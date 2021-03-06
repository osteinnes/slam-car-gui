package sdv.coupling;

import javafx.scene.image.ImageView;
import sdv.functions.video.stream.slam.SlamCam;
import sdv.functions.video.stream.webcam.WebCam;
import sdv.gui.controller.control.sys.ControlSys;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Interface class between camera stream, picture communication and TCP communication.
 *
 * @author Eirik G. Gustafsson
 * @version 23.09.2018.
 */
public class CommIn {
    // Server ip.
    private String serverIp;

    // Reads from web-cam server.
    private WebCam webCam;
    // Servers port.
    private int webCamPort;
    // Slam local port.
    private int localWebCamPort;


    // Reads from slam server.
    private SlamCam slam;
    // Slam servers webCamPort.
    private int slamPort;

    /**
     * Initial values.
     */
    public CommIn(String serverIp, int webCamPort, int localWebCamPort, int slamPort) {
        this.serverIp = serverIp;

        // Webcam info.
        this.webCamPort = webCamPort;
        this.localWebCamPort = localWebCamPort;
        this.webCam = null;


        // Slam info.
        this.slam = null;
        this.slamPort = slamPort;

    }

    /**
     * Initialises the web cam stream and draws images to ImageViewer.
     *
     * @param imageViewer Gui's image viewer, to display video feed.
     */
    public void doStartWebCam(ImageView imageViewer, ControlSys sys) {
        doStopWebCam();

        this.webCam = new WebCam(imageViewer, getInetAddress(this.serverIp), this.webCamPort, this.localWebCamPort, sys);
        this.webCam.setDaemon(true);
        this.webCam.start();
    }

    /**
     * Stops the web-cam client.
     */
    public void doStopWebCam() {
        if (null != this.webCam) {
            this.webCam.doStop();
            this.webCam.interrupt();

        }
    }

    /**
     * Initialises the SLAM image stream and draws images to ImageViewer.
     *
     * @param imageViewer Gui's image viewer, to display video feed.
     */
    public void doStartSlamCam(ImageView imageViewer, ControlSys sys) {
        if (this.slam != null) {
            this.slam.doStop();
            this.slam.interrupt();
        }
        this.slam = new SlamCam(imageViewer, getInetAddress(this.serverIp), this.slamPort, sys);
        this.slam.setDaemon(true);
        this.slam.start();
    }

    /**
     * Stops the web-cam client.
     */
    public void doStopSlamCam() {
        if (null != this.slam)
            this.slam.doStop();
    }


    /**
     * @return InetAddress, null if not found.
     */
    private InetAddress getInetAddress(String ip) {
        // Ip for InetAddress server.
        InetAddress ipAddress = null;
        try {
            ipAddress = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ipAddress;
    }
}

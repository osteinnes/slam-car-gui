package sdv.gui.controller.control.sys;

import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import sdv.coupling.CommIn;
import sdv.coupling.CommOut;

/**
 * Controls the control system GUI screen.
 */
public class ControlSys {
    // Interface for incoming communication.
    private CommIn commIn;
    // Interface for outgoing communication.
    private CommOut commOut;
    // Handles button events.
    private KeyboardInput btnEvent;
    // Holds status of manual mode button.
    @FXML private JFXToggleButton manualMode;
    // GUI's slider.
    @FXML private Slider slider;
    // GUI's ImageView, to display the images.
    @FXML private ImageView imageView;

    /**
     * Using initialise instead of constructor since this is called after FXML fields are populated.
     */
    public void initialize() {
        this.commIn = new CommIn();
        this.commOut = new CommOut();
        doHandleVideoStart();
        this.btnEvent = new KeyboardInput();
    }

    /**
     * Handles what happens when the start button is pressed.
     */
    @FXML private void handleStartBtnAction() {
    }

    /**
     * Handles what happens when the start video button is pressed.
     */
    @FXML private void doHandleVideoStart() {
        this.commIn.doStartWebCam(this.imageView);
    }

    /**
     * Handles what happens when the stop video button is pressed.
     */
    @FXML private void doHandleVideoStop() {
        this.commIn.doStopWebCam();
    }

    /**
     * Send string if it is not "".
     *
     * @param event Key pressed or released event.
     */
    @FXML private void doHandleKeyInput(KeyEvent event) {
        if (this.manualMode.isSelected()) {
            String str = this.btnEvent.doHandleKeyEvent(event);
            if (!str.equals("")) {
                this.commOut.doSendMotorControllerString(str);
            }
        }
    }

    @FXML private void doHandleSliderInput() {
        Double value = this.slider.getValue();
        int number = value.intValue();
        this.commOut.doSendMotorControllerString("SPEED:" + number);
    }
}

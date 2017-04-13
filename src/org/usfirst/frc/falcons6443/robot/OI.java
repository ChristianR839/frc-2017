package org.usfirst.frc.falcons6443.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import org.usfirst.frc.falcons6443.robot.hardware.Gamepad;
import org.usfirst.frc.falcons6443.robot.hardware.JoystickPair;

import java.util.HashMap;

//TODO Currently the port for the joystick is a placeholder, so we should change that when we know what the port is.

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 *
 * @author Christopher Medlin
 */
public class OI {

    private Gamepad gamepad;
    private Joystick leftJoystick;
    private Joystick rightJoystick;

    private HashMap<String, Button> buttons;

    /**
     * Constructor for OI.
     */
    public OI() {
    	Joystick masterStick = new Joystick(0);
    	Joystick secondaryStick = new Joystick(1);
    	
    	if (masterStick.getIsXbox()) {
            gamepad = new Gamepad(masterStick);
            leftJoystick = null;
            rightJoystick = null;
    	}
    	
    	else {
    		leftJoystick = masterStick;
    		rightJoystick = secondaryStick;
    		gamepad = null;
    	}
    	
        buttons = new HashMap<String, Button>(4);
        
        assert (gamepad != null ^ (leftJoystick != null && rightJoystick != null));
    }

    /**
     * Returns the Gamepad associated with this OI object.
     *
     * @return the Gamepad associated with this OI object.
     */
    public Gamepad getGamepad() {
        return gamepad;
    }
    
    public Joystick getLeftJoystick() {
    	return leftJoystick;
    }

    public Joystick getRightJoystick() { return rightJoystick; }

    /**
     * Returns the Button object associated with the key.
     *
     * @param key the name of the button.
     * @return the button associated with the key.
     */
    public Button getButton(String key) {
        return buttons.get(key);
    }
}

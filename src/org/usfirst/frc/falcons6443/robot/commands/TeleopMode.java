package org.usfirst.frc.falcons6443.robot.commands;

import org.usfirst.frc.falcons6443.robot.Robot;
import org.usfirst.frc.falcons6443.robot.hardware.Gamepad;
import org.usfirst.frc.falcons6443.robot.subsystems.PID;
import org.usfirst.frc.falcons6443.robot.utilities.Smashboard;

/**
 * Teleoperated mode for the robot.
 * The execute method of this class handles all possible inputs from the driver during the game.
 *
 * @author Ivan Kenevich, Christopher Medlin, Shivashriganesh Mahato
 */
public class TeleopMode extends SimpleCommand {

    private Gamepad gamepad;
    private boolean reversed, gearToggled, ropeClimberIdled;
    //private int bPressCount;
    private PID pid;

    public TeleopMode() {
        super("Teleop Command");

        requires(driveTrain);
        requires(gearHolder);
        requires(ropeClimber);
        requires(ballShooter);
    }

    @Override
    public void initialize() {
        gamepad = Robot.oi.getGamepad();
        reversed = false;
        gearToggled = false;
        ropeClimberIdled = false;
        pid = new PID(0, 0, 0, 0); //CHANGE THESE VALUES!!!!
    }

    @Override
    public void execute() {
        double throttle = gamepad.rightTrigger();
        double turn = gamepad.leftStickX();
        double ropeClimberThrottle = gamepad.leftTrigger();

        // left bumper downshifts, right bumper upshifts.
        if (gamepad.leftBumper()) {
            driveTrain.downshift();
        } else if (gamepad.rightBumper()) {
            driveTrain.upshift();
        }

        // the A button will toggle the gear holder
        if (gamepad.A()) {
            // safeguard for if the driver holds the A button
            if (!gearToggled) {
                gearHolder.open();
                gearToggled = true;
            }
        } else {
            gearHolder.close();
            gearToggled = false;
        }

        // holding the B button will start the ball shooter
        if (gamepad.B()) {
            ballShooter.spin();
            //the right trigger will start feeder wheel when the PID is done
            if (gamepad.rightTrigger() > .8 && pid.isDone()) {
                ballShooter.feeder();
            }

            //override of PID with start button
            if(gamepad.back()){
                ballShooter.feeder();
            }
        } else {
            ballShooter.stop();
        }

        // the X button will toggle the rope climber to idleing mode
        if (gamepad.X()) {
            // safeguard for if the driver holds down the X button.
            if (!ropeClimberIdled) {
                ropeClimber.toggleIdle();
                ropeClimberIdled = true;
            }
        }
        else {
            ropeClimberIdled = false;
        }

        // the Y button will toggle the drive train to reverse mode
        if (gamepad.Y()) {
            // safeguard for if the driver holds down the Y button.
            if (!reversed) {
                driveTrain.reverse();
                reversed = true;
            }
        } else {
            reversed = false;
        }

        // set the driveTrain power.
        if (throttle == 0) {
            driveTrain.spin(turn);
        } else {
            driveTrain.drive(throttle, turn);
        }

        // if the input from the joystick exceeds idle speed
        if (ropeClimberThrottle > 0.3) {
            // set the rope climber to that speed
            ropeClimber.set(ropeClimberThrottle);
        }

        Smashboard.putBoolean("reversed", driveTrain.isReversed());
    }

    public boolean isFinished() {
        return false;
    }
}

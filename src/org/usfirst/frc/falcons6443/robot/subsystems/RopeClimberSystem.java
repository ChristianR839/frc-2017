package org.usfirst.frc.falcons6443.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import org.usfirst.frc.falcons6443.robot.RobotMap;
import org.usfirst.frc.falcons6443.robot.hardware.UltrasonicSensor;

/**
 * Subsystem containing the motor for the rope climber as well as the sensor.
 *
 * @author Christopher Medlin
 */
public class RopeClimberSystem {

    private Victor motor;
    private boolean climbing;

    public RopeClimberSystem () {
        motor = new Victor (RobotMap.RopeClimberVictor);
        climbing = false;
    }

    /**
     * Turns on the rope climber motor.
     */
    public void climb () {
        motor.set(1);
        climbing = true;
    }

    /**
     * Turns off the rope climber motor.
     */
    public void stopClimbing () {
        motor.set(0);
        climbing = false;
    }

    /**
     * @return Whether or not the rope climber mechanism is climbing.
     */
    public boolean isClimbing () {
        return climbing;
    }
}

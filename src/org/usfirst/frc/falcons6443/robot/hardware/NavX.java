package org.usfirst.frc.falcons6443.robot.hardware;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

/**
 * Singleton wrapper class for the robot's NavX sensor.
 *
 * @author Shivashriganesh Mahato
 */
public class NavX {

    public static NavX instance;
    // Attitude and Heading Reference System of the NavX
    private AHRS ahrs;

    private NavX() {
        try {
            // Communicate with NavX via the MXP SPI Bus
            ahrs = new AHRS(SPI.Port.kMXP);
        } catch (RuntimeException ex) {
            // TODO Implementing means of handling and displaying exceptions for this
        }
    }

    /**
     * @return the one instance of this class.
     */
    public static NavX get() {
        if (instance == null) {
            instance = new NavX();
        }

        return instance;
    }

    /**
     * @return the AHRS object contained by this class.
     */
    public AHRS ahrs() {
        return ahrs;
    }

    /**
     * @return the yaw read from the NavX.
     */
    public float getYaw() {
        return ahrs.getYaw();
    }

//    public Vector3D getRawRotation() {
//        return new Vector3D(ahrs.getRawGyroX(), ahrs.getRawGyroY(), ahrs.getRawGyroZ());
//    }
//
//    public Vector3D getRawAccel() {
//        return new Vector3(ahrs.getRawAccelX(), ahrs.getRawAccelY(), ahrs.getRawAccelZ());
//    }
//
//    public Vector3D getRawMagnetism() {
//        return new Vector3(ahrs.getRawMagX(), ahrs.getRawMagY(), ahrs.getRawMagZ());
//    }
}

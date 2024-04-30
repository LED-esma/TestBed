package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.team5430.util.SwerveModule;
import com.team5430.util.SwerveModuleGroup;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class TestBed extends SubsystemBase {

  public TestBed() {}

  // constants

  public AHRS gyro = new AHRS(Port.kMXP);

  SwerveModuleGroup DriveTrain = new SwerveModuleGroup(
  new SwerveModule(0, 1),
  new SwerveModule(6, 7));

  SwerveModule moduleA = new SwerveModule(0, 1);
  SwerveModule moduleB = new SwerveModule(6, 7);

 

  public void motorConfig() {
    SmartDashboard.putData("module A", moduleA);
    SmartDashboard.putData("Module B", moduleB);
    SmartDashboard.putData("Gyroscope", gyro);
  }

  // setAngle will set the directional angle
  public void drive(double wantedAngle, double throttle, double z, double gyroscope) {
    DriveTrain.Drive(deadzone(wantedAngle, throttle), throttle, z, gyroscope);
  }

  // **the wheel will go to the position that is greater than 0.2, otherwise stop power when less
  // than or equal to*/
  public double deadzone(double angle, double power) {
    double lastAngle;
    // If the input given is less than 0.2 the rotation will reset to 0
    if (power >= -15) {
      lastAngle = angle;
      if (power <= -15) {
        return lastAngle;
      }
    }
    return angle;
  }

  @Override
  public void periodic() {
    SmartDashboard.updateValues();
    SmartDashboard.putNumber("degrees", RobotContainer.driverJoystick.getDirectionDegrees());
  }
}

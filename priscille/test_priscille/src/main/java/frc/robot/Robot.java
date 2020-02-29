/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Spark;

//import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;

/**
 * This is a demo program showing the use of the DifferentialDrive class.
 * Runs the motors with arcade steering.
 */
public class Robot extends TimedRobot {

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
 
  private static final int kFrontLeftChannel = 2;
  private static final int kRearLeftChannel = 4;
  private static final int kFrontRightChannel = 1;
  private static final int kRearRightChannel = 3;
  private static final int kRamasseurChannel = 5;
  private static final int kVerin1Cannel = 6;

  private static final int kJoystickChannel = 0;

  private final Joystick m_stick = new Joystick(kJoystickChannel);
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  SpeedController m_frontLeft = new Spark(kFrontLeftChannel);
  SpeedController m_rearLeft = new Spark(kRearLeftChannel);
  SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
  SpeedController m_frontRight = new Spark(kFrontRightChannel);
  SpeedController m_rearRight = new Spark(kRearRightChannel);
  SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
  Spark m_ramasseur  = new Spark(kRamasseurChannel);
  Spark m_verin1 = new Spark(kVerin1Cannel);
  BasculeActivation m_bascule = new BasculeActivation();
  @Override
  public void robotInit() {
  m_frontLeft.setInverted(true);
  m_rearLeft.setInverted(true);
  m_rearRight.setInverted(true);
  m_frontRight.setInverted(true);

  }
  @Override
  public void robotPeriodic() {

  Color detectedColor = m_colorSensor.getColor();

  double IR = m_colorSensor.getIR();

  SmartDashboard.putNumber("Red", detectedColor.red);
  SmartDashboard.putNumber("Green", detectedColor.green);
  SmartDashboard.putNumber("Blue", detectedColor.blue);
  SmartDashboard.putNumber("IR", IR);
  }
  DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
  
  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    double f = m_stick.getY();
    boolean sortie = m_bascule.compute(m_stick.getRawButton(7));
    SmartDashboard.putBoolean("bascule", sortie);
    if (sortie){
   f = -f;
  }
    m_drive.arcadeDrive(f, -m_stick.getX());}
    int indexBtnEject = 4;{
    if (SmartDashboard.getBoolean("estDroitier", true)) {
      indexBtnEject = 5;
    }
    double r = 0;
    if (m_stick.getRawButton(1)) {
      r = -0.554688;
    } else if (m_stick.getRawButton(indexBtnEject)) {
      r = 0.554688;
    }
    m_ramasseur.setSpeed(r);
    SmartDashboard.putNumber("ramasseur", r);
    double v=0;
    if (m_stick.getRawButton(3)) {
    v=1;
    } else if (m_stick.getRawButton(2)) {
      v=-1;
    }
    m_verin1.setSpeed(v);
    SmartDashboard.putNumber("v", v);
  }
}

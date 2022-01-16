/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

  TalonSRX leftMotor1 = new TalonSRX(DriveConstants.kLeftMotor1Port);
  //TalonSRX leftMotor2 = new TalonSRX(DriveConstants.kLeftMotor2Port);
  
  TalonSRX rightMotor1 = new TalonSRX(DriveConstants.kRightMotor1Port);
  //TalonSRX rightMotor2 = new TalonSRX(DriveConstants.kRightMotor2Port);

  /*
  how to set up sparkmaxes, if your robot has those
  CANSparkMax leftMotor1 = new CANSparkMax(DriveConstants.kLeftMotor1Port, MotorType.kBrushless);
  CANSparkMax leftMotor2 = new CANSparkMax(DriveConstants.kLeftMotor2Port, MotorType.kBrushless);

  CANSparkMax rightMotor1 = new CANSparkMax(DriveConstants.kRightMotor1Port, MotorType.kBrushless);
  CANSparkMax rightMotor2 = new CANSparkMax(DriveConstants.kRightMotor2Port, MotorType.kBrushless);
  */
  //  Creates a new DriveSubsystem.
  public DriveSubsystem() {
    
  }

  /**
   * Drives the robot using tank drive controls
   * Tank drive is slightly easier to code but less intuitive to control, so this is here as an example for now
   * @param leftPower the commanded power to the left motors
   * @param rightPower the commanded power to the right motors
   */

  public void tankDrive(double leftPower, double rightPower) {
    leftMotor1.set(ControlMode.PercentOutput, leftPower);
    rightMotor1.set(ControlMode.PercentOutput, rightPower);

    //if using a sparkmax
    // leftMotor1.set(leftPower);
    // rightMotor1.set(rightPower);
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param forward the commanded forward movement
   * @param turn the commanded turn rotation
   */

  public void arcadeDrive(double throttle, double turn) {
    throttle = -throttle;
    turn *= 0.7;
    leftMotor1.set(ControlMode.PercentOutput,  -(throttle + turn));
    rightMotor1.set(ControlMode.PercentOutput, (throttle - turn) * 0.75);
  }

    
  public void modArcadeDrive1(double throttle, double turn) {
    double leftOut =throttle;
    double rightOut=throttle;
    if (turn > 0){
      rightOut += turn;
    } else if (turn < 0){
      leftOut += -turn;
    }

    if (leftOut > 1){
      rightOut = rightOut - (leftOut - 1);
      leftOut = 1;
    } else if (rightOut > 1){
      leftOut = leftOut - (rightOut - 1);
      rightOut = 1;
    }    

    leftMotor1.set(ControlMode.PercentOutput, leftOut);
    rightMotor1.set(ControlMode.PercentOutput, rightOut);
  }

  public double expoKS(double base, double exponent){
    //weird stuff will hapen if you don't put a number > 0
    double finVal = Math.pow(Math.abs(base),exponent);
    if (base < 0) {
      finVal *= -1;
    }
    return finVal;
  }

}

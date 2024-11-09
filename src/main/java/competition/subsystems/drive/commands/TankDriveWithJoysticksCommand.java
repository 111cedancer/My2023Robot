package competition.subsystems.drive.commands;

import javax.inject.Inject;

import competition.operator_interface.OperatorInterface;
import competition.subsystems.drive.DriveSubsystem;
import org.apache.logging.log4j.LogManager;
import xbot.common.command.BaseCommand;
import xbot.common.controls.actuators.XCANSparkMax;
import xbot.common.math.MathUtils;

public class TankDriveWithJoysticksCommand extends BaseCommand {

    final DriveSubsystem driveSubsystem;
    final OperatorInterface oi;


    @Inject
    public TankDriveWithJoysticksCommand(OperatorInterface oi, DriveSubsystem driveSubsystem) {
        this.oi = oi;
        this.driveSubsystem = driveSubsystem;
        this.addRequirements(this.driveSubsystem);
    }

    @Override
    public void initialize() {
        log.info("Initializing");
    }

    @Override
    public void execute() {
        driveSubsystem.tankDrive(
            MathUtils.deadband(oi.gamepad.getLeftVector().getY(), 0.15), 
            MathUtils.deadband(oi.gamepad.getRightVector().getX(), 0.15)
        );
    }
}

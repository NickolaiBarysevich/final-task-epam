package com.epam.hotelbooking.command.factory;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommandConstants;
import com.epam.hotelbooking.command.admin.AssignRoomCommand;
import com.epam.hotelbooking.command.admin.ManagementCommand;
import com.epam.hotelbooking.command.admin.RoomChooseCommand;
import com.epam.hotelbooking.command.client.*;
import com.epam.hotelbooking.command.common.*;
import com.epam.hotelbooking.dao.manager.DaoManager;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.UnknownCommandException;
import com.epam.hotelbooking.service.*;

public class CommandFactory {

    public Command create(String command) {
        try (DaoManager daoManager = new DaoManager()) {
            switch (command) {
                case CommandConstants.LOGIN:
                    UserService userService = new UserService(daoManager.getUserDao());
                    return new LoginCommand(userService);

                case CommandConstants.EXIT:
                    return new ExitCommand();

                case CommandConstants.PROFILE:
                    return new ProfileCommand();

                case CommandConstants.MANAGEMENT:
                    ApplicationDtoService applicationDtoService =
                            new ApplicationDtoService(daoManager.getApplicationDtoDao(), daoManager.getApplicationDao());
                    return new ManagementCommand(applicationDtoService);

                case CommandConstants.ROOM_CHOOSE:
                    RoomDtoService roomDtoService = new RoomDtoService(daoManager.getRoomDtoDao());
                    applicationDtoService = new ApplicationDtoService(daoManager.getApplicationDtoDao(), daoManager.getApplicationDao());
                    return new RoomChooseCommand(roomDtoService, applicationDtoService);

                case CommandConstants.ASSIGN_ROOM:
                    RoomService roomService = new RoomService(daoManager.getRoomDao());
                    BillService billService = new BillService(daoManager.getBillDao());
                    ApplicationService applicationService = new ApplicationService(daoManager.getApplicationDao());
                    return new AssignRoomCommand(billService, roomService, applicationService);

                case CommandConstants.REGISTER_APPLICATION:
                    applicationService = new ApplicationService(daoManager.getApplicationDao());
                    return new RegisterApplicationCommand(applicationService);

                case CommandConstants.SHOW_LOGIN:
                    return new ShowLoginCommand();

                case CommandConstants.SHOW_HOME:
                    return new ShowHomePageCommand();

                case CommandConstants.SHOW_INFO:
                    ApplicationBillDtoService applicationBillDtoService =
                            new ApplicationBillDtoService(daoManager.getApplicationBillDtoDao());
                    return new ShowInfoCommand(applicationBillDtoService);

                case CommandConstants.ERROR:
                    return new ErrorCommand();

                case CommandConstants.SHOW_REGISTRATION:
                    return new ShowRegistrationCommand();

                case CommandConstants.REGISTRATION:
                    userService = new UserService(daoManager.getUserDao());
                    return new RegistrationCommand(userService);

                case CommandConstants.APPLICATION_HISTORY:
                    applicationBillDtoService = new ApplicationBillDtoService(daoManager.getApplicationBillDtoDao());
                    return new ApplicationHistoryCommand(applicationBillDtoService);

                case CommandConstants.CANCEL_CONSIDERING:
                    applicationService = new ApplicationService(daoManager.getApplicationDao());
                    return new CancelConsideringCommand(applicationService);

                case CommandConstants.SHOW_BALANCE:
                    return new ShowBalanceCommand();

                case CommandConstants.ADD_BALANCE:
                    userService = new UserService(daoManager.getUserDao());
                    return new AddBalanceCommand(userService);

                case CommandConstants.PAY:
                    applicationService = new ApplicationService(daoManager.getApplicationDao());
                    userService = new UserService(daoManager.getUserDao());
                    billService = new BillService(daoManager.getBillDao());
                    roomService = new RoomService(daoManager.getRoomDao());
                    return new PayCommand(applicationService, userService, billService, roomService);

                case CommandConstants.CANCEL_APPROVED:
                    billService = new BillService(daoManager.getBillDao());
                    applicationService = new ApplicationService(daoManager.getApplicationDao());
                    roomService = new RoomService(daoManager.getRoomDao());
                    return new CancelApprovedCommand(billService, roomService, applicationService);

                case CommandConstants.NOT_FOUND:
                    return new NotFoundCommand();
                default:
                    throw new UnknownCommandException(command);
            }
        } catch (DaoException e) {
            return new ErrorCommand();
        }

    }
}

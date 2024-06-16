package com.gtrxac.discord;

import javax.microedition.lcdui.*;
import cc.nnproject.json.*;

public class ChannelSelector extends List implements CommandListener {
    State s;
    private Command backCommand;
    private Command refreshCommand;
    private Command markChannelReadCommand;
    private Command markGuildReadCommand;

    public ChannelSelector(State s) throws Exception {
        super(s.selectedGuild.name, List.IMPLICIT);
        setCommandListener(this);
        this.s = s;

        for (int i = 0; i < s.channels.size(); i++) {
            Channel ch = (Channel) s.channels.elementAt(i);
            append(ch.toString(s), null);
        }

        backCommand = new Command("Back", Command.BACK, 0);
        refreshCommand = new Command("Refresh", Command.ITEM, 1);
        markChannelReadCommand = new Command("Mark as read", Command.ITEM, 2);
        markGuildReadCommand = new Command("Mark all as read", Command.ITEM, 3);
        addCommand(backCommand);
        addCommand(refreshCommand);

        if (s.channels.size() > 0) {
            addCommand(markChannelReadCommand);
            addCommand(markGuildReadCommand);
        }
    }

    /**
     * Updates the unread/ping indicators for channel names shown in this selector.
     */
    public void update() {
        for (int i = 0; i < s.channels.size(); i++) {
            Channel ch = (Channel) s.channels.elementAt(i);
            set(i, ch.toString(s), null);
        }
    }

    public void commandAction(Command c, Displayable d) {
        if (c == backCommand) {
            s.openGuildSelector(false);
        }
        if (c == refreshCommand) {
            s.openChannelSelector(true);
        }
        if (c == markChannelReadCommand) {
            Channel ch = (Channel) s.channels.elementAt(getSelectedIndex());
            s.unreads.markRead(ch);
            s.updateUnreadIndicators();
        }
        if (c == markGuildReadCommand) {
            s.unreads.markRead(s.selectedGuild);
            s.updateUnreadIndicators();
        }
        if (c == List.SELECT_COMMAND) {
            s.isDM = false;
            s.selectedChannel = (Channel) s.channels.elementAt(getSelectedIndex());
            s.openChannelView(true);
        }
    }
}
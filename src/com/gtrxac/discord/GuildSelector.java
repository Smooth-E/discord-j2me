package com.gtrxac.discord;

import javax.microedition.lcdui.*;
import cc.nnproject.json.*;

public class GuildSelector extends List implements CommandListener {
    State s;
    private Command backCommand;
    private Command refreshCommand;
    private Command dmCommand;
    private Command settingsCommand;

    public GuildSelector(State s) throws Exception {
        super("Servers", List.IMPLICIT);
        setCommandListener(this);
        this.s = s;

        Guild.fetchGuilds(s);
        for (int i = 0; i < s.guilds.size(); i++) {
            append(((Guild) s.guilds.elementAt(i)).name, null);
        }

        backCommand = new Command("Back", Command.BACK, 0);
        refreshCommand = new Command("Refresh", Command.ITEM, 1);
        dmCommand = new Command("Direct messages", Command.ITEM, 2);
        settingsCommand = new Command("Settings", Command.ITEM, 3);
        addCommand(backCommand);
        addCommand(refreshCommand);
        addCommand(dmCommand);
        addCommand(settingsCommand);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == backCommand) {
            s.disp.setCurrent(new LoginForm(s));
        }
        if (c == refreshCommand) {
            s.openGuildSelector(true);
        }
        if (c == List.SELECT_COMMAND) {
            Guild newGuild = (Guild) s.guilds.elementAt(getSelectedIndex());

            if (s.selectedGuild == null || newGuild.id != s.selectedGuild.id) {
                s.selectedGuild = newGuild;
                s.openChannelSelector(true);
            } else {
                s.openChannelSelector(false);
            }
        }
        if (c == dmCommand) {
            s.disp.setCurrent(new DMSelector(s));
        }
        if (c == settingsCommand) {
            s.disp.setCurrent(new SettingsForm(s));
        }
    }
}

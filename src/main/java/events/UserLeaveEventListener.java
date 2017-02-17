package events;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.member.UserLeaveEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import utils.FileManager;
import utils.MessageSender;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by Phost on 07.02.2017.
 */
public class UserLeaveEventListener {


    @EventSubscriber
    public void onUserLeaveEvent(UserLeaveEvent event) throws IOException, RateLimitException, DiscordException, MissingPermissionsException {
        if (FileManager.checkIfAlreadyExists(event.getGuild()) != null) {
            if (ZonedDateTime.now(ZoneId.of(FileManager.checkIfAlreadyExists(event.getGuild()))).getMinute() >= 10)
                MessageSender.sendMessage("User " + event.getUser().mention() + " just left/got kicked from " + event.getGuild().getName() + "!" + " ```" + ZonedDateTime.now(ZoneId.of(FileManager.checkIfAlreadyExists(event.getGuild()))).getDayOfMonth() + "." + ZonedDateTime.now(ZoneId.of(FileManager.checkIfAlreadyExists(event.getGuild()))).getMonthValue() + "." + ZonedDateTime.now(ZoneId.of(FileManager.checkIfAlreadyExists(event.getGuild()))).getYear() + " " + ZonedDateTime.now(ZoneId.of(FileManager.checkIfAlreadyExists(event.getGuild()))).getHour() + ":" + ZonedDateTime.now(ZoneId.of(FileManager.checkIfAlreadyExists(event.getGuild()))).getMinute() + "```", event.getGuild().getChannelByID(event.getGuild().getID()));
            else
                MessageSender.sendMessage("User " + event.getUser().mention() + " just left/got kicked from " + event.getGuild().getName() + "!" + " ```" + ZonedDateTime.now(ZoneId.of(FileManager.checkIfAlreadyExists(event.getGuild()))).getDayOfMonth() + "." + ZonedDateTime.now(ZoneId.of(FileManager.checkIfAlreadyExists(event.getGuild()))).getMonthValue() + "." + ZonedDateTime.now(ZoneId.of(FileManager.checkIfAlreadyExists(event.getGuild()))).getYear() + " " + ZonedDateTime.now(ZoneId.of(FileManager.checkIfAlreadyExists(event.getGuild()))).getHour() + ":0" + ZonedDateTime.now(ZoneId.of(FileManager.checkIfAlreadyExists(event.getGuild()))).getMinute() + "```", event.getGuild().getChannelByID(event.getGuild().getID()));
        }else{
            MessageSender.sendMessage("```Phost\nCouldn't send message with leave/kick info because no timezone was set on this server! To do so type '♥settimezone <yourTimezone>'\n```", event.getGuild().getChannelByID(event.getGuild().getID()));
        }
    }
}

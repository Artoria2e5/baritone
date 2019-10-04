/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone;

import baritone.api.IBaritone;
import baritone.api.IBaritoneProvider;
import baritone.api.bot.IUserManager;
import baritone.api.cache.IWorldScanner;
import baritone.api.bot.IBaritoneUser;
import baritone.bot.UserManager;
import baritone.utils.command.BaritoneChatControl;
import baritone.cache.WorldScanner;
import baritone.utils.player.PrimaryPlayerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brady
 * @since 9/29/2018
 */
public final class BaritoneProvider implements IBaritoneProvider {

    private final Baritone primary;

    {
        this.primary = new Baritone(PrimaryPlayerContext.INSTANCE);

        // Setup chat control, just for the primary instance
        new BaritoneChatControl(this.primary);
    }

    @Override
    public IBaritone getPrimaryBaritone() {
        return primary;
    }

    @Override
    public List<IBaritone> getAllBaritones() {
        List<IBaritone> baritones = new ArrayList<>();
        baritones.add(getPrimaryBaritone());
        for (IBaritoneUser ibu : UserManager.INSTANCE.users()) {
            baritones.add(ibu.getBaritone());
        }
        return baritones;
    }

    @Override
    public IWorldScanner getWorldScanner() {
        return WorldScanner.INSTANCE;
    }

    @Override
    public IUserManager getUserManager() {
        return UserManager.INSTANCE;
    }
}

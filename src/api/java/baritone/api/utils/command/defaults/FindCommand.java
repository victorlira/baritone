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

package baritone.api.utils.command.defaults;

import baritone.api.Settings;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.command.Command;
import baritone.api.utils.command.datatypes.BlockById;
import baritone.api.utils.command.helpers.arguments.ArgConsumer;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class FindCommand extends Command {
    public FindCommand() {
        super("find", "Find positions of a certain block");
    }

    @Override
    protected void executed(String label, ArgConsumer args, Settings settings) {
        List<Block> toFind = new ArrayList<>();

        while (args.has()) {
            toFind.add(args.getDatatypeFor(BlockById.class));
        }

        BetterBlockPos origin = ctx.playerFeet();

        toFind.stream()
            .flatMap(block ->
                ctx.worldData().getCachedWorld().getLocationsOf(
                    Block.REGISTRY.getNameForObject(block).getPath(),
                    Integer.MAX_VALUE,
                    origin.x,
                    origin.y,
                    4
                ).stream()
            )
            .map(BetterBlockPos::new)
            .map(BetterBlockPos::toString)
            .forEach(this::logDirect);
    }

    @Override
    protected Stream<String> tabCompleted(String label, ArgConsumer args, Settings settings) {
        return args.tabCompleteDatatype(BlockById.class);
    }

    @Override
    public List<String> getLongDesc() {
        return asList(
            "",
            "",
            "Usage:",
            "> "
        );
    }
}
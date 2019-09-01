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

package baritone.api.utils.command.argparser;

import baritone.api.utils.command.argument.CommandArgument;

public interface IArgParser<T> {
    /**
     * @return the class of this parser.
     */
    Class<T> getKlass();

    interface Stateless<T> extends IArgParser<T> {
        /**
         * @param arg The argument to parse.
         * @return What it was parsed into.
         * @throws RuntimeException if you want the parsing to fail. The exception will be caught and turned into an
         *                          appropriate error.
         */
        T parseArg(CommandArgument arg) throws RuntimeException;
    }

    interface Stated<T, S> extends IArgParser<T> {
        Class<S> getStateKlass();

        /**
         * @param arg   The argument to parse.
         * @param state Can be anything.
         * @return What it was parsed into.
         * @throws RuntimeException if you want the parsing to fail. The exception will be caught and turned into an
         *                          appropriate error.
         */
        T parseArg(CommandArgument arg, S state) throws RuntimeException;
    }
}
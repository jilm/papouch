/*
 *  Copyright 2016 Jiri Lidinsky
 *
 *  This file is part of control4j.
 *
 *  control4j is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, version 3.
 *
 *  control4j is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with control4j.  If not, see <http://www.gnu.org/licenses/>.
 */

package cz.lidinsky.papouch;

import cz.lidinsky.spinel.SpinelException;
import cz.lidinsky.spinel.SpinelMessage;

/**
 *
 */
public class AD4 extends Papouch {

    public static final int MEASUREMENT = 0x51;


    /**
     * Decode one time measurement response message and returns measured
     * values for each chanel. The value may be from range 0 - 10 000.
     *
     * @param message
     * @return
     * @throws SpinelException
     */
    public static int[] getOneTimeMeasurement(SpinelMessage message)
            throws SpinelException {
        int[] values = new int[4];
        if (message.getInst() == 0) {
            for (int i = 0; i < 4; i++) {
                int offset = i * 4;
                values[i] = (int) (message.getData(offset + 2) * 0x100
                        + message.getData(offset + 3));
            }
            return values;
        } else {
            throw new SpinelException();
        }
    }

    /**
     * Decode one time measurement response message and returns status for
     * each chanel.
     *
     * @param message
     * @return
     * @throws SpinelException
     */
    public static int[] getStatus(SpinelMessage message) throws SpinelException {
        int[] values = new int[4];
        if (message.getInst() == 0) {
            for (int i = 0; i < 4; i++) {
                int offset = i * 4;
                values[i] = (int) message.getData(offset + 1);
            }
            return values;
        } else {
            throw new SpinelException();
        }
    }

}
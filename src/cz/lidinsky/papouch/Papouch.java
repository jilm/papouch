/*
 *  Copyright 2013 Jiri Lidinsky
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

public class Papouch
{
  public static final int PRODUCTION_INFO_READ = 0xf3;

  protected int address;

  public static ProductionInfo getProductionInfo(SpinelMessage message)
  throws SpinelException
  {
    if (message.getInst() == 0)
    {
      int[] data = new int[message.getDataLength()];
      int index = 0;
      int offset = 0;
      while (message.getData(index) != 0x3b)
      {
        data[index - offset] = message.getData(index);
	index++;
      }
      String type = new String(data, 0, index);
      index++;
      offset = index;
      while (message.getData(index) != 0x3b)
      {
        data[index - offset] = message.getData(index);
	index++;
      }
      String version = new String(data, 0, index-offset+1);
      index++;
      offset = index;
      for (; index<message.getDataLength(); index++)
        data[index-offset] = message.getData(index);
      String other = new String(data, 0, index-offset+1);
      return new ProductionInfo(type, version, other);
    }
    else
      throw new SpinelException();
  }

}

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


public class ProductionInfo
{
  private String type;
  private String version;
  private String other;

  public ProductionInfo(String type, String version, String other)
  {
    this.type = type;
    this.version = version;
    this.other = other;
  }

  public String getType()
  {
    return type;
  }

  public String getVersion()
  {
    return version;
  }

  public String getOther()
  {
    return other;
  }
}


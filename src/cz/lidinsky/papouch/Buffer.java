/*
 * Copyright (C) 2016 jilm
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cz.lidinsky.papouch;

/**
 * It is buffer for data storage. Each value is identified by the integer
 * number which corresponds to the INS number.
 */
public class Buffer {

  private final Object[] buffer;

  public Buffer() {
    this.buffer = new Object[256];
  }

  public Object get(int code) {
    return buffer[code];
  }

  public void put(int code, Object data) {
    buffer[code] = data;
  }

}

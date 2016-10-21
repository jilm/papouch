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
 *
 */
public enum TQS3Codes {

  MEASUREMENT (0x51, 1),
  COMMUNICATION_PARAM_READ (0xF0, 2),
  COMMUNICATION_PARAM_SET (0xE0, 0),
  COMMUNICATION_ERROR_READ (0xF4, 1),
  STATUS_READ (0xF1, 1),
  STATUS_SET (0xE1, 0),
  USER_DATA_READ (0xF2, 16),
  USER_DATA_SET (0xE2, 17),
  CHECKSUM_READ (0xFE, 1),
  CHECKSUM_ENABLE (0xEE, 1),
  ADDRESS_CHANGE (0xEB, 0),
  CONFIGURATION_ENABLE (0xE4, 0),
  RESET (0xE3, 1),
  COMMUNICATION_PROTOCOL_SET (0xED, 0),
  SERIAL_NUMBER_READ (0xFA, 8);

  private final int instCode;
  private final int responseLen;

  TQS3Codes(int instCode, int responseLen) {
    this.instCode = instCode;
    this.responseLen = responseLen;
  }

}

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

/**
 * A thermometer from the papouch manufacturrer.
 *
 * <p>Methods of this class can decode reply of the thermometer.
 *
 * <p>This class could be used to download settings of the TQS module and save
 * it into the data structure.
 */
public class TQS3 extends Papouch {

  public static final int MEASUREMENT = 0x51;
  public static final int COMMUNICATION_PARAM_READ = 0xf0;
  public static final int COMMUNICATION_PARAM_SET = 0xe0;
  public static final int COMMUNICATION_ERROR_READ = 0xf4;
  //public static final int MODEL_NUMBER_READ = 0xf3;
  public static final int STATUS_READ = 0xf1;
  public static final int STATUS_SET = 0xe1;
  public static final int USER_DATA_READ = 0xf2;
  public static final int USER_DATA_SET = 0xe2;
  public static final int CHECKSUM_READ = 0xfe;
  public static final int CHECKSUM_ENABLE = 0xee;
  public static final int ADDRESS_CHANGE = 0xeb;
  public static final int CONFIGURATION_ENABLE = 0xe4;
  public static final int RESET = 0xe3;
  public static final int COMMUNICATION_PROTOCOL_SET = 0xed;
  public static final int SERIAL_NUMBER_READ = 0xfa;

  /**
   * An array which contains all of the instruction codes supported by this
   * module.
   */
  public static final int[] INST_CODES = new int[] {
    MEASUREMENT, COMMUNICATION_PARAM_READ, COMMUNICATION_PARAM_SET,
    COMMUNICATION_ERROR_READ, STATUS_READ, STATUS_SET, USER_DATA_READ,
    USER_DATA_SET, CHECKSUM_READ, CHECKSUM_ENABLE, ADDRESS_CHANGE,
    CONFIGURATION_ENABLE, RESET, COMMUNICATION_PROTOCOL_SET,
    SERIAL_NUMBER_READ
  };

  /**
   * Decode message which is a replay for one time measurement request.
   *
   * @param message
   *            a message to be decoded
   *
   * @return a temperature
   *
   * @throws SpinelException
   *            if the message is not a temperature response message
   */
  public static double getOneTimeMeasurement(SpinelMessage message)
  throws SpinelException {
    if (message.getInst() == 0) {
      short rawValue = (short)((message.getData(0) << 8) + message.getData(1));
      double value = (double)rawValue;
      value = Math.floor(value / 3.2d + 0.5d) * 0.1d;
      return value;
    } else {
      throw new SpinelException();
    }
  }

  /**
   * Creates and returns a spinel message which could be used as a response
   * to the given one time measurement request.
   *
   * @param request
   *            must be a valid request message for the one time measurement
   *
   * @param temperature
   *            which will be returned in the returned response
   *
   * @return a message that is a reply to the given request
   *
   * @throws cz.lidinsky.spinel.SpinelException
   *            if the given request is not a request for the one time
   *            measurement
   */
  public static SpinelMessage getOneTimeMeasurementResponse(
      SpinelMessage request, double temperature) throws SpinelException {
    if (request.getInst() == MEASUREMENT) {
      int rawValue = Math.round((float)(temperature * 32.0d));
      int[] data = new int[2];
      data[0] = (rawValue >> 8) & 0xff;
      data[1] = rawValue & 0xff;
      return request.getAckMessage(SpinelMessage.ACK_OK, data);
    } else {
      throw new SpinelException();
    }
  }

  public static SpinelMessage getOneTimeMeasurementRequest() {
    return new SpinelMessage(0, MEASUREMENT);
  }

  public static CommunicationParams getCommunicationParams(SpinelMessage message)
  throws SpinelException
  {
    if (message.getInst() == 0)
      return new CommunicationParams(message.getData(0), message.getData(1));
    else
      throw new SpinelException();
  }

  public static int getStatus(SpinelMessage message) throws SpinelException
  {
    if (message.getInst() == 0)
      return message.getData(0);
    else
      throw new SpinelException();
  }

  public static int getCommunicationErrors(SpinelMessage message)
  throws SpinelException
  {
    if (message.getInst() == 0)
      return message.getData(0);
    else
      throw new SpinelException();
  }

  public static String getUserData(SpinelMessage message) throws SpinelException
  {
    if (message.getInst() == 0)
    {
      int[] buffer = new int[16];
      for (int i=0; i<16; i++) buffer[i] = message.getData(i);
      return new String(buffer, 0, 16);
    }
    else
      throw new SpinelException();
  }

  public static SerialNumber getSerialNumber(SpinelMessage message)
  throws SpinelException
  {
    if (message.getInst() == 0)
    {
      int productNumber = message.getData(0) * 0x100 + message.getData(1);
      int serialNumber = message.getData(2) * 0x100 + message.getData(3);
      long other = message.getData(4) * 0x1000000l
        + message.getData(5) * 0x10000l + message.getData(6) * 0x100l
	+ message.getData(7);
      return new SerialNumber(productNumber, serialNumber, other);
    }
    else
      throw new SpinelException();
  }

  public static boolean isChecksumEnabled(SpinelMessage message)
  throws SpinelException
  {
    if (message.getInst() == 0)
    {
      if (message.getData(0) == 0)
        return false;
      else if (message.getData(0) == 1)
        return true;
      else
        throw new SpinelException();
    }
    else
      throw new SpinelException();
  }

}


/*
 * Copyright (C) 2016 jilm
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the

implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cz.lidinsky.papouch;

import cz.lidinsky.spinel.SpinelInputStream;
import cz.lidinsky.spinel.SpinelMessage;
import cz.lidinsky.spinel.SpinelOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A program that can reply to the spinel requests.
 */
public class Simulator {

  public static void main(String[] args) throws Exception {

    System.out.println("Simulator server started...");
    ServerSocket server = new ServerSocket(12341);
    System.out.println(String.format("Going to listen on port %d ...", server.getLocalPort()));
    Socket socket = server.accept();
    System.out.println("connection accepted...");
    SpinelInputStream is = new SpinelInputStream(socket.getInputStream());
    SpinelOutputStream os = new SpinelOutputStream(socket.getOutputStream());

    while (true) {
      SpinelMessage request = is.readMessage();
      System.out.println("request: " + request.toString());
      SpinelMessage response = TQS3.getOneTimeMeasurementResponse(request, 13.5);
      System.out.println("response: " + response.toString());
      os.write(response);
    }

  }

}

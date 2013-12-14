/* -*- java -*-
 *
 * (C) 2013 Ulrich Kuehn <ukuehn@acm.org>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */

package net.ukuehn.connect;

import java.net.*;
import java.io.*;

import java.util.StringTokenizer;



public class ConnectMain {

	static final String version = "0.1";

	static final String usage =
		"Connect version "+version+" by "
		+"Ulrich Kuehn <ukuehn@acm.org>\n\n"
		+"Usage:\n"
		+"java -jar connect.jar [options] host:port\n"
		+"  where options are:\n\n"
		+"    -q     no output, result is in return code\n\n";


	protected static void usage() {
		System.err.println(usage);
	}


	public static void main(String args[]) {

		String host = null;
		String sPort = null;
		int port = 0;
		boolean verbose = true;
		int nextopt;

		for (nextopt = 0;  nextopt < args.length;  nextopt += 1) {
			/* handle options */
			if (!args[nextopt].startsWith("-")) {
				break;
			}
			if (args[nextopt].equals("-q")) {
				verbose = false;
			} else {
				/* wrong option given */
				usage();
				System.exit(-1);
			}
		}

		/* handle non-option arguments */
		if (nextopt >= args.length) {
			/* if nothing is given, all is fine :) */
			if (verbose) {
				usage();
			}
			System.exit(0);
		}
		
		String hostParm = args[nextopt];
		
		StringTokenizer st = new StringTokenizer(hostParm, ":");
		if (st.hasMoreTokens()) {
			host = st.nextToken();
		}
		if (st.hasMoreTokens()) {
			sPort = st.nextToken();
		}
		if ((host == null) || (sPort == null)) {
			usage();
			System.exit(-1);
		}
		try {
			port = Integer.parseInt(sPort);
		} catch (NumberFormatException e) {
			if (verbose) {
				System.err.println("Port must be a number");
			}
			System.exit(-1);
		}

		try {
			Socket s = new Socket(host, port);
			s.close();
		} catch (IOException e) {
			if (verbose) {
				System.out.println(e.toString());
			}
			System.exit (1);
		}
		System.exit(0);
	}


}


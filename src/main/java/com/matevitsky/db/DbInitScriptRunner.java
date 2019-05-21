package com.matevitsky.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DbInitScriptRunner {

    public static void executeScript(Connection conn, InputStream in)
            throws SQLException {
        Scanner s = new Scanner(in);
        s.useDelimiter("/\\*[\\s\\S]*?\\*/|--[^\\r\\n]*|;");

        Statement st = null;

        try {
            st = conn.createStatement();

            while (s.hasNext()) {
                String line = s.next().trim();

                if (!line.isEmpty()) {
                    st.execute(line);
                }
            }
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }
}

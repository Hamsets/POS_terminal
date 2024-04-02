package pos.Connection;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ConnectionSettingsObj {
    private String strMessage;
    private String urlServer;
    private int portServer;
    private static final String TAG = "logsConnectionSettingsObj";

}

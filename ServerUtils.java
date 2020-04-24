//
//  Mark Anthony Start          180140208       C02220 CW2
//

public final class ServerUtils {

        private ServerUtils() {
        }

	private static final String EOM = "21e86386-1818-4e8f-8ba5-7e65191e69f7"; //The magic string that signifies End Of Message. generated using UUID.randomUUID(). Probably overkill for our needs.
	
	public static String getEOM() {
		return EOM;
	}
}

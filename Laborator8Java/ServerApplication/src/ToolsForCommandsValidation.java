public class ToolsForCommandsValidation {
    static boolean isValidCommand ( String string ) {
        if ( isRegisterCommand (string) )
            return true;
        if ( isLoginCommand (string) )
            return true;
        if ( isSendCommand (string) )
            return true;
        if ( isReadCommand (string) )
            return true;
        if ( isFriendCommand (string) )
            return true;
        if ( isUploadCommand (string) )
            return true;
        if ( isStopCommand (string) )
            return true;
        return (isExitCommand (string));
    }

    static boolean isStopCommand ( String string ) {
        return string.equals ("stop");
    }

    static boolean isUploadCommand ( String string ) {
        String pattern = "upload";
        return string.matches (pattern);
    }

    static boolean isRegisterCommand ( String string ) {
        String pattern = "register\\s+[a-zA-Z]+";
        return string.matches (pattern);
    }

    static boolean isLoginCommand ( String string ) {
        String pattern = "login\\s+[a-zA-Z]+";
        return string.matches (pattern);
    }

    static boolean isSendCommand ( String string ) {
        String pattern = "send\\s+(.)+";
        return string.matches (pattern);
    }

    static boolean isSeeNetworkStructure ( String string ) {
        String pattern = "see";
        return string.matches (pattern);
    }

    static boolean isReadCommand ( String string ) {
        String pattern = "read";
        return string.matches (pattern);
    }

    static boolean isReadNewCommand ( String string ) {
        String pattern = "read new";
        return string.matches (pattern);
    }

    static boolean isFriendCommand ( String string ) {
        String pattern = "friend(\\s+[a-zA-Z]+)+";
        return string.matches (pattern);
    }

    static boolean isExitCommand ( String string ) {
        return (string.equalsIgnoreCase ("exit") || string.contains ("exit"));
    }
}

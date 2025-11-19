package okbu.cs1;

public class CableTVchannels {
    int userChannel;
    int channelType;

    public CableTVchannels(int userChannel, int channelType){
        this.userChannel = userChannel;
        this.channelType = channelType;
    }

    public int getUserChannel() {
        return userChannel;
    }
    public void setUserChannel(int userChannel) {
        this.userChannel = userChannel;
    }
    public int getChannelType() {
        if ((userChannel >= 2) && (userChannel <=499)){
            return 1;
        }
        else if ((userChannel >= 1002) && (userChannel <= 1499)){
            return 2;
        }
        else{
            return 3;
        }
    }
    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }

    
}

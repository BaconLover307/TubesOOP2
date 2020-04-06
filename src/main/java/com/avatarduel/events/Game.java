public class GameChannel implements EventChannel {
  final int topicMax = 100;
  final int subscriberMax = 100;
  private String[] topics;
  private int topicNum;
  private Subscriber[][] subscribers;
  private int[] topicSubscribers;

  public GameChannel() {
    this.topics = new String[topicMax];
    this.topicNum = 0;
    this.subscribers = new Subscriber[topicMax][subscriberMax];
    this.topicSubscribers = new int[topicMax];
    for (int i = 0; i< topicMax; i++){
      this.topicSubscribers[i] = 0;
    }
  }

  public void addSubscriber(String topic, Subscriber s) {
    // tambahkan s sebagai subscriber ke topic yang diberikan
    int i = 0;
    boolean found = false;

    while ((i < topicNum) && (!found)){
      if (this.topics[i].equals(topic)){
        found = true;
      } else {
        i++;
      }
    }
    //if topic doesn't exist, add it in
    if (!found){
      this.topics[i] = topic; 
      this.topicNum++;
    }
    this.subscribers[i][this.topicSubscribers[i]] = s; 
    this.topicSubscribers[i]++;
  }

  public void sendEvent(String topic, Event event) {
    // untuk setiap subscriber s yang sudah subscribe ke topic yang diberikan,
    // panggil s.onEvent(event)
    int i = 0;
    boolean found = false;
    
    while ((i < topicNum) && (!found)){
      if (this.topics[i].equals(topic)){
        found = true;
      } else {
        i++;
      }
    }
    
    if (found){
      for (int j = 0; j < this.topicSubscribers[i]; j++){
        this.subscribers[i][j].onEvent(event);
      }
    }
  }
}
package com.melvic.ranker.topics;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    List<Topic> listTopics();

    Optional<Topic> getTopic(String topicId);

    void addTopic(Topic topic);

    void updateTopic(Topic topic);

    void deleteTopic(String topicId);
}

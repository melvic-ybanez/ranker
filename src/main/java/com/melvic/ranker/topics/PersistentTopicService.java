package com.melvic.ranker.topics;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersistentTopicService implements TopicService {
    private final TopicRepository itemRepository;

    public PersistentTopicService(TopicRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Topic> listTopics() {
        return Streamable.of(itemRepository.findAll()).toList();
    }

    @Override
    public Optional<Topic> getTopic(String topicId) {
        return itemRepository.findById(topicId);
    }

    @Override
    public void addTopic(Topic topic) {
        itemRepository.save(topic);
    }

    @Override
    public void updateTopic(Topic topic) {
        itemRepository.save(topic);
    }

    @Override
    public void deleteTopic(String topicId) {
        itemRepository.deleteById(topicId);
    }
}

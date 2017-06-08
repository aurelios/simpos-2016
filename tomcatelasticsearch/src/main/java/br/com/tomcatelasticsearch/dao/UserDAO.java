package br.com.tomcatelasticsearch.dao;

import br.com.tomcatelasticsearch.model.User;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO {
   
    public List<String> readAllUsers() {
        Settings settings = Settings.builder()
                .put("client.transport.sniff", true)
                .put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        SearchResponse response = client.prepareSearch("myapp").setTypes("person").execute().actionGet();
        List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());
        List<User> results = new ArrayList<User>();
        List<String> data = new ArrayList<String>();
        searchHits.forEach(hit -> data.add(hit.getSourceAsString()));
        return data;
    }
}

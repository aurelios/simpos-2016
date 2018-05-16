package br.com.javaelasticsearch.dao;

import br.com.javaelasticsearch.model.Person;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHit;
import sun.rmi.transport.Transport;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonDAO {
   
    public List<String> readAllUsers() {
        Settings settings = Settings.builder()

                .put("client.transport.ping_timeout", "30s")
                .put("client.transport.sniff", true)

                .build();
        TransportClient client = null;
        try {
            client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
            //client = new PreBuiltTransportClient(settings)
              //      .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        SearchResponse response = client.prepareSearch("simpos").setTypes("person").setSize(500000).execute().actionGet();
        client.close();
        List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());
        List<Person> results = new ArrayList<Person>();
        List<String> data = new ArrayList<String>();
        for(SearchHit hit: searchHits){
            data.add(hit.getSourceAsString());
        }

        return data;
    }
}

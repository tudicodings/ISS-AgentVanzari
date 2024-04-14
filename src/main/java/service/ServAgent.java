package service;

import domain.Agent;
import repository.RepoAgent;

import java.io.IOException;
import java.util.List;

public class ServAgent {
    RepoAgent agentRepository;
    public ServAgent(RepoAgent ar){this.agentRepository = ar;}
    public List<Agent> getallAgents(){return agentRepository.getALL();}
    public void addAgent(int ID, String nume, String parola) throws Exception {
        if(parola.length() < 5 && !parola.endsWith("AV")){
            throw new Exception("Wrong password");
        }
        Agent agent = new Agent(ID, nume, parola);
        agentRepository.add(agent);
    }
}

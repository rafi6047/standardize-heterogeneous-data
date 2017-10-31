package se.kth.epe.degreeproject.standardizeheterogeneousdata.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.domain.Port;

import java.util.Map;

@Repository
public interface PortRepository extends GraphRepository<Port> {

    Port findByDefaultPort(String defaultPort);

    Port findByDefaultPortAndEntry(String defaultPort, String entry);

    Port findByServiceName(String serviceName);

    Port findByServiceNameAndEntry(String serviceName, String entry);

    Port findByLearnedServiceAliasListContaining(String serviceName);

    @Query("MATCH p=shortestPath((o:Port)-[:IS_A*]->(d:OntologyRoot))\n" +
            "WHERE \n" +
            "  o.defaultPort =~ '22/tcp'\n" +
            "RETURN nodes(p) as ports,relationships(p)")
    Iterable<Map<String, Object>> myCustomQuery(@Param("aParam") String param);

//    @Query("MATCH (n:Port {portNo:22}),(m:OntologyRoot), p =(n)-[:IS_A*]->(m) RETURN p")
    @Query("MATCH (n:Port),(m:OntologyRoot), p =(n)-[:IS_A*]->(m) WHERE n.defaultPort={defaultPort} RETURN p")
    Iterable<Map<String, Object>> findByPortNoTillRoot(@Param("defaultPort") String defaultPort);
//    Port findByPortNoTillRoot(@Param("portNo") String portNo);

//
//    @Query("MATCH (m:Movie)<-[rating:RATED]-(user) WHERE id(movie)={movie} return rating")
//    List<Rating> getRatings(@Param("movie") Movie movie);
//
//    // Co-Actors
//    Set<Person> findByActorsMoviesActorName(String name);
//
//    @Query("MATCH (movie:Movie)-[:HAS_GENRE]->(genre)<-[:HAS_GENRE]-(similar)
//            WHERE id(movie) = {0} RETURN similar")
//            List<Movie> findSimilarMovies(Movie movie);


}

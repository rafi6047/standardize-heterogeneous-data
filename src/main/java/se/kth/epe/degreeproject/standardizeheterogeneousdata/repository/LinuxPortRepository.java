package se.kth.epe.degreeproject.standardizeheterogeneousdata.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.domain.LinuxPort;

@Repository
public interface LinuxPortRepository extends GraphRepository<LinuxPort> {

    // derived finder
    LinuxPort findByPortNo(Integer portNo);

    // derived finder
    LinuxPort findByServiceName(String serviceName);

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

package se.kth.epe.degreeproject.standardizeheterogeneousdata.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.kth.epe.degreeproject.standardizeheterogeneousdata.domain.CommonNode;

import java.util.List;
import java.util.Map;

@Repository
public interface CommonNodeRepository extends GraphRepository<CommonNode> {

    List<CommonNode> findAllByName(String name);
    List<CommonNode> findByNodeId(Long nodeId);
    List<CommonNode> findAllByKeyword(String keyword);
    CommonNode findFirstByKeyword(String keyword);
    CommonNode findFirstByClassType(String classType);
    CommonNode findByDefaultPort(String defaultPort);
    List<CommonNode> findByLearnedKeywordListContaining(String serviceName);


    @Query("START startNode = node({startId}), endNode = node({endId})" +
            "            MATCH paths = (startNode)-[*]->(endNode)" +
            "            RETURN nodes(paths) AS nodes, EXTRACT(node IN nodes(paths) | ID(node)) AS ids")
    Iterable<Map<String, Iterable<Object>>> findAllPaths(@Param("startId") long startId, @Param("endId") long endId);

    @Query("match (n) where n.keyword is not null return n.keyword;")
    List<String> findAllKeywords();
}
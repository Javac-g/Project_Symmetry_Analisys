import com.max.hexopt.core.topology.Mesh;
import com.max.hexopt.core.topology.Vertex;

import java.util.List;
import java.util.stream.Collectors;

public final class VertexCloud {

    public static List<Vertex> fromMesh(Mesh mesh) {
        return mesh.vertices().stream().collect(Collectors.toList());
    }
}

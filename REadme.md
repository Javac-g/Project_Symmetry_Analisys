Project Structure
com.max.hexopt
│
├── HexOptApplication
│
├── config
│   └── JacksonConfig
│
├── controller
│   ├── TilingController
│   ├── OptimizationController
│   └── SymmetryController
│
├── service
│   ├── tiling
│   │   ├── TilingService
│   │   ├── SquareTilingService
│   │   ├── TriangleTilingService
│   │   ├── HexagonTilingService
│   │   └── VoronoiTilingService
│   │
│   ├── geometry
│   │   ├── GeometryService
│   │   ├── AreaCalculator
│   │   ├── PerimeterCalculator
│   │   ├── PolygonValidator
│   │   └── ConvexHullService
│   │
│   ├── symmetry
│   │   ├── SymmetryService
│   │   ├── RotationService
│   │   ├── ReflectionService
│   │   └── GroupClassifier
│   │
│   └── optimization
│       ├── OptimizationService
│       ├── EnergyFunction
│       └── SimulatedAnnealingEngine
│
├── model
│   ├── core
│   │   ├── Point
│   │   ├── Edge
│   │   ├── Polygon
│   │   └── Tiling
│   │
│   ├── symmetry
│   │   ├── SymmetryGroup
│   │   └── Transformation
│   │
│   └── dto
│       ├── TilingRequest
│       ├── TilingResponse
│       └── OptimizationResponse
│
└── util
    └── MathUtils

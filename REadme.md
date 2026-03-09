# HexOpt

HexOpt is a geometry optimization and tiling analysis engine built with **Java** and **Spring Boot**.
The project provides tools for generating tilings, analyzing geometric structures, detecting symmetry groups, and optimizing configurations using algorithms such as simulated annealing.

---

# Project Architecture

The application follows a layered architecture:

* **Controller Layer** – REST endpoints
* **Service Layer** – Core computational logic
* **Model Layer** – Domain objects and DTOs
* **Utility Layer** – Mathematical helpers and common tools

---

# Project Structure

```
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
```

---

# Module Overview

## Controllers

### `TilingController`

Handles API endpoints for generating tilings such as square, triangular, hexagonal, and Voronoi patterns.

### `OptimizationController`

Provides endpoints for running optimization algorithms on tilings or geometric structures.

### `SymmetryController`

Exposes functionality for detecting symmetry operations and classifying symmetry groups.

---

## Services

### Tiling Services

Responsible for constructing different tessellation patterns.

* `SquareTilingService`
* `TriangleTilingService`
* `HexagonTilingService`
* `VoronoiTilingService`

### Geometry Services

Core computational geometry utilities.

* Area calculation
* Perimeter calculation
* Polygon validation
* Convex hull generation

### Symmetry Services

Detects geometric symmetries and transformations.

* Rotational symmetry
* Reflection symmetry
* Group classification

### Optimization Services

Implements opt

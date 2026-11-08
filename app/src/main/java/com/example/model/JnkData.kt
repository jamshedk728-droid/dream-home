package com.example.model

object JnkData {
    val serviceCategories = listOf(
        ServiceCategory(
            id = "srv_home",
            title = "Home Interior Design",
            subtitle = "Complete End-to-End Residential Interiors",
            description = "Customized luxury home interior solutions tailored to your lifestyle, incorporating space planning, mood lighting, and bespoke furnishings.",
            keyFeatures = listOf("Comprehensive 3D Layouts", "Custom Color Schemes", "Space Optimization", "Ergonomic Furniture"),
            iconName = "home"
        ),
        ServiceCategory(
            id = "srv_pop",
            title = "POP Ceiling & False Ceiling",
            subtitle = "Master Ceiling Craftsmanship & LED Lighting",
            description = "Expert POP ceiling designs with indirect LED cove lights, multi-tiered drops, and decorative moulding that transform living spaces.",
            keyFeatures = listOf("Gypsum & POP Work", "Linear LED Profiles", "Geometric Ceiling Drops", "Thermal & Acoustic Insulation"),
            iconName = "ceiling"
        ),
        ServiceCategory(
            id = "srv_kitchen",
            title = "Modular Kitchen",
            subtitle = "Ergonomic & High-Gloss Smart Kitchens",
            description = "Precision engineered modular kitchens with waterproof marine ply, soft-close hardware, pull-out baskets, and quartz countertops.",
            keyFeatures = listOf("L-Shape / U-Shape / Island", "Soft-Close Tandem Drawers", "Chimney & Hob Provision", "Moisture Resistant Finish"),
            iconName = "kitchen"
        ),
        ServiceCategory(
            id = "srv_tv",
            title = "TV Unit & Wall Paneling",
            subtitle = "Statement Media Walls & Louvers",
            description = "Fluted wall paneling, charcoal louvers, Italian marble backdrops, and floating media consoles with hidden cable management.",
            keyFeatures = listOf("Italian Marble & WPC Panel", "Concealed Wiring Channels", "Warm Ambient LED Strips", "Integrated Display Shelves"),
            iconName = "tv"
        ),
        ServiceCategory(
            id = "srv_wardrobe",
            title = "Custom Wardrobes",
            subtitle = "Sliding & Hinged Luxury Wardrobes",
            description = "Floor-to-ceiling wardrobes with lacquer glass, tinted mirrors, sensor wardrobe lighting, and organized accessory drawers.",
            keyFeatures = listOf("Sliding & Hinged Doors", "Lacquer Glass & Tinted Mirrors", "Internal LED Motion Sensors", "Custom Lockers & Drawers"),
            iconName = "wardrobe"
        ),
        ServiceCategory(
            id = "srv_furniture",
            title = "Wooden Furniture",
            subtitle = "Bespoke Teak & Engineered Wood Pieces",
            description = "Handcrafted sofas, dining tables, study tables, hydraulic storage beds, and accent chairs built to fit exact dimensions.",
            keyFeatures = listOf("Teak Wood Craftsmanship", "High-Density Foam Sofa", "Hydraulic Bed Lift Mechanism", "Scratch-Resistant Polish"),
            iconName = "furniture"
        ),
        ServiceCategory(
            id = "srv_commercial",
            title = "Commercial & Office Interiors",
            subtitle = "Modern Workspaces & Retail Outlets",
            description = "Functional and inspiring commercial environments, reception counters, glass partition walls, and ergonomic workstation clusters.",
            keyFeatures = listOf("Acoustic Partitioning", "Brand Alignment Aesthetic", "Ergonomic Workstations", "Executive Cabin Design"),
            iconName = "office"
        ),
        ServiceCategory(
            id = "srv_renovation",
            title = "Renovation & Remodeling",
            subtitle = "Transform Existing Homes into Modern Luxury",
            description = "Complete home makeover services, stripping outdated layouts to install modern false ceilings, wall paneling, and updated flooring.",
            keyFeatures = listOf("Wall Demolition & Rebuilding", "Tile & Marble Upgrades", "Electrical Rewiring", "Turnkey Handover"),
            iconName = "renovation"
        )
    )

    val portfolioItems = listOf(
        PortfolioItem(
            id = "port_01",
            title = "Grand Royal Living Room & Cove Ceiling",
            category = "Living Room",
            subcategory = "Luxury Interior",
            description = "Spacious living room featuring gold-accented POP ceiling, indirect cove lighting, marble wall paneling, and plush L-shaped velvet seating.",
            sqFtPriceRange = "₹180 - ₹280 / sq.ft",
            roomType = "Living Room",
            features = listOf("Warm Cove Lighting", "Fluted WPC Wall Panel", "Custom TV Console", "False Ceiling Grid"),
            gradientColorsHex = listOf(0xFF2E2E2E, 0xFF6E4B3A, 0xFFC7A248),
            isFeaturedHero = true
        ),
        PortfolioItem(
            id = "port_02",
            title = "Geometric Step POP False Ceiling",
            category = "POP Ceiling",
            subcategory = "Ceiling Mastercraft",
            description = "Multi-tiered geometric POP false ceiling designed with magnetic track lights and recessed linear LED profiles.",
            sqFtPriceRange = "₹85 - ₹130 / sq.ft",
            roomType = "Living / Bedroom",
            features = listOf("Step Gypsum Board", "Magnetic Track Light", "Indirect Ambience", "Anti-Crack Finish"),
            gradientColorsHex = listOf(0xFF3E2B20, 0xFFC7A248, 0xFFD8C8B8),
            isPopCeilingSpecial = true
        ),
        PortfolioItem(
            id = "port_03",
            title = "Acrylic High-Gloss Modular Kitchen",
            category = "Kitchen",
            subcategory = "Modular Kitchen",
            description = "U-shaped acrylic high-gloss modular kitchen with quartz countertop, tandem drawers, and under-cabinet LED warm lights.",
            sqFtPriceRange = "₹1,400 - ₹2,200 / sq.ft",
            roomType = "Kitchen",
            features = listOf("Waterproof Marine Ply", "Soft-Close Hardware", "Pull-Out Pantry Unit", "Granite/Quartz Counter"),
            gradientColorsHex = listOf(0xFF1E293B, 0xFF475569, 0xFFC7A248)
        ),
        PortfolioItem(
            id = "port_04",
            title = "Floating TV Console with Louver Paneling",
            category = "TV Units",
            subcategory = "Media Wall",
            description = "Floor-to-ceiling charcoal louver paneling paired with back-lit white translucent marble slate and floating drawer console.",
            sqFtPriceRange = "₹35,000 - ₹85,000 / unit",
            roomType = "Living Room",
            features = listOf("Charcoal Louver Slats", "Concealed Cable Pass-through", "Subtle LED Strip", "Push-to-Open Drawers"),
            gradientColorsHex = listOf(0xFF2A201A, 0xFF6E4B3A, 0xFFD8C8B8)
        ),
        PortfolioItem(
            id = "port_05",
            title = "Master Suite Floor-to-Ceiling Sliding Wardrobe",
            category = "Wardrobes",
            subcategory = "Custom Storage",
            description = "8-foot tall sliding wardrobe with copper tinted mirror panels, dark walnut frame, and integrated sensor wardrobe lights.",
            sqFtPriceRange = "₹1,200 - ₹1,800 / sq.ft",
            roomType = "Master Bedroom",
            features = listOf("Top-Hanging Sliding Track", "Tinted Mirror Door", "In-built Locker Unit", "Velvet Jewelry Organizer"),
            gradientColorsHex = listOf(0xFF3B2F2F, 0xFF8B5E3C, 0xFFC7A248),
            isFurnitureSpecial = true
        ),
        PortfolioItem(
            id = "port_06",
            title = "Wooden Rafter Ceiling with Warm Chandelier",
            category = "POP Ceiling",
            subcategory = "Wooden Ceiling",
            description = "Combination of gypsum POP ceiling with teak finish wooden rafters creating a warm rustic-modern ambience.",
            sqFtPriceRange = "₹110 - ₹160 / sq.ft",
            roomType = "Dining Area",
            features = listOf("Natural Teak Rafters", "Recessed Spotlights", "Centrally Mounted Chandelier", "Moisture Proof Coating"),
            gradientColorsHex = listOf(0xFF4A3525, 0xFF8B5E3C, 0xFFFAF8F5),
            isPopCeilingSpecial = true
        ),
        PortfolioItem(
            id = "port_07",
            title = "Modern Master Bedroom with Cushioned Bed Headboard",
            category = "Bedroom",
            subcategory = "Bedroom Interior",
            description = "Cozy master bedroom interior with upholstered velvet headboard wall panel, floating side tables, and dimmable cove lights.",
            sqFtPriceRange = "₹150 - ₹250 / sq.ft",
            roomType = "Bedroom",
            features = listOf("Custom Fabric Panel", "Hydraulic Bed Base", "Floating Nightstands", "Warm Pendant Lights"),
            gradientColorsHex = listOf(0xFF2E2E2E, 0xFF6E4B3A, 0xFFE7E7E7)
        ),
        PortfolioItem(
            id = "port_08",
            title = "8-Seater Teak Wood Dining Suite & Crockery Unit",
            category = "Furniture",
            subcategory = "Dining & Cabinets",
            description = "Solid teak wood 8-seater dining table with glass-top crockery console and brass inlay detailing.",
            sqFtPriceRange = "₹65,000 - ₹1,20,000 / set",
            roomType = "Dining Room",
            features = listOf("Pure Teak Wood", "Brass Inlay Accents", "Glass Showcase Display", "Ergonomic Cushioning"),
            gradientColorsHex = listOf(0xFF5A3E2B, 0xFFC7A248, 0xFFFAF8F5),
            isFurnitureSpecial = true
        ),
        PortfolioItem(
            id = "port_09",
            title = "Executive Corporate Office Reception & False Ceiling",
            category = "Commercial",
            subcategory = "Office Interior",
            description = "Sleek office reception counter with backlit logo display, acoustic ceiling tiles, and glass meeting cabin partitions.",
            sqFtPriceRange = "₹160 - ₹240 / sq.ft",
            roomType = "Office / Commercial",
            features = listOf("Acrylic Backlit Logo", "Glass Partitions", "Modular Reception Desk", "Acoustic Ceiling Panels"),
            gradientColorsHex = listOf(0xFF1E293B, 0xFF334155, 0xFF94A3B8)
        )
    )

    val processSteps = listOf(
        ProcessStep(1, "Consultation", "Understanding Your Dreams", "We meet to discuss your lifestyle, vision, requirements, timeline, and budget limits."),
        ProcessStep(2, "Site Visit & Measurement", "Precise Laser Survey", "Our expert team visits your property in Tuni or surrounding areas to take exact dimensions and analyze lighting."),
        ProcessStep(3, "Space Planning & Layout", "Optimizing Every Inch", "We present 2D floor layout plans detailing furniture placement, electrical points, and ceiling grids."),
        ProcessStep(4, "3D Design & Rendering", "Visualize Before Execution", "See realistic 3D visualizations of your living room, kitchen, POP ceilings, and wardrobes before work starts."),
        ProcessStep(5, "Material Selection", "Handpicking Quality", "Choose plywood, laminates, acrylics, POP grades, hardware, and lighting from certified premium brands."),
        ProcessStep(6, "Execution & Carpentry", "On-Site Craftsmanship", "Skilled carpenters, POP ceiling specialists, and electricians carry out work with strict quality checks."),
        ProcessStep(7, "Final Handover", "Polished & Ready to Move In", "Deep cleaning, final light inspection, and handover of your dream space with warranty documents.")
    )

    val whyChooseUs = listOf(
        WhyChooseUsItem("Premium Materials", "We strictly use termite-proof marine ply, branded POP, and durable hardware.", "materials"),
        WhyChooseUsItem("Expert Designers", "12+ years of experience crafting luxury residential and commercial spaces.", "designers"),
        WhyChooseUsItem("Custom Designs", "Every home is unique. We customize ceilings, furniture, and layouts to your exact taste.", "custom"),
        WhyChooseUsItem("Affordable Pricing", "Transparent quotation with no hidden costs. Best value for your investment.", "pricing"),
        WhyChooseUsItem("Timely Delivery", "Strict adherence to project schedules with regular progress updates.", "delivery"),
        WhyChooseUsItem("Modern Concepts", "Latest trending false ceiling profiles, magnetic tracks, and smart storage.", "modern"),
        WhyChooseUsItem("Quality Workmanship", "Master artisans for POP moulding, seamless laminates, and sturdy carpentry.", "quality"),
        WhyChooseUsItem("Customer Satisfaction", "320+ happy families across Tuni, Kakinada, Rajahmundry & Vizag.", "satisfaction")
    )

    val testimonials = listOf(
        Testimonial(
            clientName = "Srikanth Verma",
            location = "Balaji Complex, Tuni",
            rating = 5,
            review = "JNK Interior Designer completely transformed our 3BHK home! Their POP ceiling work with LED linear profiles is mind-blowing. Highly recommended in Tuni!",
            projectType = "Complete 3BHK Home Interior",
            date = "June 2026"
        ),
        Testimonial(
            clientName = "Priyanka Raju",
            location = "Kakinada",
            rating = 5,
            review = "The modular kitchen and sliding wardrobe quality is top notch. They used marine ply as promised and finished the work 5 days ahead of schedule.",
            projectType = "Modular Kitchen & Wardrobe",
            date = "May 2026"
        ),
        Testimonial(
            clientName = "Venkat Rao",
            location = "Rajahmundry",
            rating = 5,
            review = "Their TV unit design with louver paneling and indirect warm lighting became the centerpiece of our home. Very professional team and reasonable pricing.",
            projectType = "Living Room & TV Unit",
            date = "April 2026"
        ),
        Testimonial(
            clientName = "Anil Kumar",
            location = "Tuni",
            rating = 5,
            review = "Great craftsmanship for false ceilings! JNK team is easily accessible, honest with pricing, and gave 3D drawings before starting.",
            projectType = "POP False Ceiling & Office Interior",
            date = "March 2026"
        )
    )
}

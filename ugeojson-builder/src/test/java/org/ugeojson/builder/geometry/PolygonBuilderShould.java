package org.ugeojson.builder.geometry;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.ugeojson.builder.exception.InvalidPolygonDtoException;
import org.ugeojson.builder.geometry.PolygonBuilder;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.geometry.LineStringDto;
import org.ugeojson.model.geometry.PolygonDto;

public class PolygonBuilderShould {

	@Test(expected = InvalidPolygonDtoException.class) public void
	throw_invalid_polygon_exception(){
		PolygonDto polygon = new PolygonDto();
		
		LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645)));
		LineStringDto lineString2 = new LineStringDto(Arrays.asList(new PositionDto(12.2365, -14.8987),new PositionDto(63.254, 28.778)));
		
		polygon.setLinearRings(Arrays.asList(lineString1,lineString2));
		PolygonBuilder.getInstance().toGeoJSON(polygon);
	}
	
	@Test public void
	build_polygon_by_correcting_linearrings_if_three_positions_exist(){
		PolygonDto polygon = new PolygonDto();
		
		LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645)));
		LineStringDto lineString2 = new LineStringDto(Arrays.asList(new PositionDto(12.2365, -14.8987),new PositionDto(63.254, 28.778)));
		LineStringDto lineString3 = new LineStringDto(Arrays.asList(new PositionDto(12.2365, -14.8987),new PositionDto(63.254, 28.778)));
		
		lineString1.getPositions().add(new PositionDto(44.44,45,55));
		lineString2.getPositions().add(new PositionDto(34,45,15.78));
		lineString3.getPositions().add(new PositionDto(10.44,58,55));
		
		polygon.setLinearRings(Arrays.asList(lineString1,lineString2,lineString3));
		String geometryGeoJSON = PolygonBuilder.getInstance().toGeoJSON(polygon);
		assertThat(geometryGeoJSON, equalTo("{\n\"type\": \"Polygon\",\n\"coordinates\": [\n[\n [32.123, 24.587],\n [36.1478, 29.3645],\n [44.44, 45.0, 55.0],\n [32.123, 24.587]\n],\n[\n [12.2365, -14.8987],\n [63.254, 28.778],\n [34.0, 45.0, 15.78],\n [12.2365, -14.8987]\n],\n[\n [12.2365, -14.8987],\n [63.254, 28.778],\n [10.44, 58.0, 55.0],\n [12.2365, -14.8987]\n]\n]\n}"));
	}
	
	@Test public void
	build_polygon(){
		PolygonDto polygon = new PolygonDto();
		LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645)));
		lineString1.getPositions().add(new PositionDto(44.44,45,55));
		
		polygon.setLinearRings(Arrays.asList(lineString1));
		String geometryGeoJSON = PolygonBuilder.getInstance().toGeoJSON(polygon);
		assertThat(geometryGeoJSON, equalTo("{\n\"type\": \"Polygon\",\n\"coordinates\": [\n[\n [32.123, 24.587],\n [36.1478, 29.3645],\n [44.44, 45.0, 55.0],\n [32.123, 24.587]\n]\n]\n}"));
	}
	
}

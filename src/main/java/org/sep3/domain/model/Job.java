package org.sep3.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Job {

	private int id;

	private int title;

	private int description;

	private int location;

	private int price;

	private int dateTime;

}

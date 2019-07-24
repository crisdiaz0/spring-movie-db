package io.qdivision.qtp.movies.Names;

import io.qdivision.qtp.movies.LikedStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("names")
@Slf4j
public class NameController {

	private final NameRepository nameRepository;

	public NameController(NameRepository nameRepository) {
		this.nameRepository = nameRepository;
	}

	@GetMapping
	public List<Name> getNames() {
		return nameRepository.findAll()
				.stream()
				.map(this::toName)
				.collect(Collectors.toList());
	}

	@GetMapping
	@RequestMapping("/{id}")
	public Name findById(@PathVariable("id") Long id) {
		return toName(nameRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Name not found"))
		);
	}

	@PutMapping
	@RequestMapping("/{id}/{likedStatus}")
    public void updateLikedStatus(@PathVariable("id") Long id , @PathVariable("likedStatus") String newStatus) {

        Name nameToBeUpdated = findById(id);
        nameToBeUpdated.setLikedStatus(LikedStatus.valueOf(newStatus));
		log.info("STATUS: {}", newStatus);
		log.info("NEW NAME: {}", nameToBeUpdated.getLikedStatus());

		nameRepository.saveAndFlush(toNameEntity(nameToBeUpdated));
    }

	private NameEntity toNameEntity(Name name) {
		return NameEntity.builder()
				.id(name.getId())
				.nconst(name.getNconst())
				.primaryName(name.getPrimaryName())
				.birthYear(name.getBirthYear())
				.deathYear(name.getDeathYear())
				.primaryProfession(name.getPrimaryProfession())
				.knownForTitles(name.getKnownForTitles())
				.likedStatus(name.getLikedStatus())
				.build();
	}

	private Name toName(NameEntity nameEntity) {
		return Name.builder()
				.id(nameEntity.getId())
				.nconst(nameEntity.getNconst())
				.primaryName(nameEntity.getPrimaryName())
				.birthYear(nameEntity.getBirthYear())
				.deathYear(nameEntity.getDeathYear())
				.primaryProfession(nameEntity.getPrimaryProfession())
				.knownForTitles(nameEntity.getKnownForTitles())
				.likedStatus(nameEntity.getLikedStatus())
				.build();
	}
}

package domain.db;

import domain.model.Contact;
import domain.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDBinMemory implements PersonDB {
	private final Map<String, Person> persons = new HashMap<>();

	/* CLASS IS NOT BEING USED */

	public PersonDBinMemory() {
		/*
		Person administrator = new Person("admin", "admin@ucll.be", "t", "Ad", "Ministrator");
		Person elonMusk = new Person("elonmusk", "elonmusk@tesla.be", "t", "Elon", "Musk");
		Person jefBezos = new Person("jefbezos", "jefbezos@amazon.be", "t", "Jef", "Bezos");
		add(elonMusk);
		add(jefBezos);
		add(administrator);*/
	}

	@Override
	public Person get(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		return persons.get(personId);
	}

	@Override
	public List<Person> getAll(){
		return new ArrayList<Person>(persons.values());
	}

	@Override
	public void add(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if (persons.containsKey(person.getUserid())) {
			throw new DbException("User already exists");
		}
		persons.put(person.getUserid(), person);
	}

	// NOT YET IMPLEMENTED
	@Override
	public void update(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if(!persons.containsKey(person.getUserid())){
			throw new DbException("No person found");
		}
		persons.put(person.getUserid(), person);
	}

	@Override
	public void delete(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		persons.remove(personId);
	}

	@Override
	public Person searchPerson(String userid){
		if(userid == null){
			throw new DbException("User id not found.");
		}
		return persons.get(userid);
	}

	@Override
	public Person getPersonIfAuthenticated(String userid, String password){
		Person person = searchPerson(userid);
		if(person != null && person.isCorrectPassword(password))
			return person;
		return null;
	}

	@Override
	public void addContact(Contact contact) {
		// NOT IMPLEMENTED
	}

	@Override
	public List<Contact> getAllContacts() {
		// NOT IMPLEMENTED
		return null;
	}

	@Override
	public void deleteContact(String firstName, String lastName, String contactDate, String contactHour) {
		// NOT IMPLEMENTED
	}

}

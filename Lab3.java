import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.prefs.*;

public class Lab3 extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JButton addButton;
	private JLabel addressLabel;
	private JPanel addressPanel;
	private JTextField addressTextField;
	private JPanel buttonPanel;
	private JLabel cityLabel;
	private JPanel cityStatePanel;
	private JTextField cityTextField;
	private JButton deleteButton;
	private JButton findButton;
	private JButton firstButton;
	private JLabel givenNameLabel;
	private JPanel givenNamePanel;
	private JTextField givenNameTextField;
	private JButton lastButton;
	private JButton nextButton;
	private JButton previousButton;
	private JLabel stateLabel;
	private JTextField stateTextField;
	private JLabel surnameLabel;
	private JPanel surnamePanel;
	private JTextField surnameTextField;
	private JButton updateButton;
	
	String bookFile = null;
	String indexFile = null;
	
	RandomAccessFile index; 
	RandomAccessFile book;

	public Lab3() {
		setTitle("Address Book");
		setBounds(100, 100, 704, 239);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new java.awt.GridLayout(5, 0));

		surnamePanel = new JPanel();
		surnameLabel = new JLabel();
		surnameTextField = new JTextField();
		givenNamePanel = new JPanel();
		givenNameLabel = new JLabel();
		givenNameTextField = new JTextField();
		addressPanel = new JPanel();
		addressLabel = new JLabel();
		addressTextField = new JTextField();
		cityStatePanel = new JPanel();
		cityLabel = new JLabel();
		cityTextField = new JTextField();
		stateLabel = new JLabel();
		stateTextField = new JTextField();
		buttonPanel = new JPanel();
		firstButton = new JButton();
		nextButton = new JButton();
		previousButton = new JButton();
		lastButton = new JButton();
		findButton = new JButton();
		addButton = new JButton();
		deleteButton = new JButton();
		updateButton = new JButton();

		surnamePanel.setName("surnamePanel");

		surnameLabel.setText("Surname");
		surnameLabel.setName("surnameLabel");
		surnamePanel.add(surnameLabel);

		surnameTextField.setColumns(45);
		surnameTextField.setText("");
		surnameTextField.setName("surnameTextField");
		surnamePanel.add(surnameTextField);

		getContentPane().add(surnamePanel);

		givenNamePanel.setName("givenNamePanel");

		givenNameLabel.setText("Given Names");
		givenNameLabel.setName("givenNameLabel");
		givenNamePanel.add(givenNameLabel);

		givenNameTextField.setColumns(45);
		givenNameTextField.setText("");
		givenNameTextField.setName("givenNameTextField");
		givenNamePanel.add(givenNameTextField);

		getContentPane().add(givenNamePanel);

		addressPanel.setName("addressPanel");

		addressLabel.setText("Street Address");
		addressLabel.setName("addressLabel");
		addressPanel.add(addressLabel);

		addressTextField.setColumns(45);
		addressTextField.setText("");
		addressTextField.setName("addressTextField");
		addressPanel.add(addressTextField);

		getContentPane().add(addressPanel);

		cityStatePanel.setName("cityStatePanel");

		cityLabel.setText("City");
		cityLabel.setName("cityLabel");
		cityStatePanel.add(cityLabel);

		cityTextField.setColumns(30);
		cityTextField.setText("");
		cityTextField.setName("cityTextField");
		cityStatePanel.add(cityTextField);

		stateLabel.setText("State");
		stateLabel.setName("stateLabel");
		cityStatePanel.add(stateLabel);

		stateTextField.setColumns(5);
		stateTextField.setText("");
		stateTextField.setName("stateTextField");
		cityStatePanel.add(stateTextField);

		getContentPane().add(cityStatePanel);

		buttonPanel.setName("buttonPanel");

		firstButton.setText("First");
		firstButton.setName("firstButton");
		firstButton.addActionListener(this);
		buttonPanel.add(firstButton);

		nextButton.setText("Next");
		nextButton.setName("nextButton");
		nextButton.addActionListener(this);
		buttonPanel.add(nextButton);

		previousButton.setText("Previous");
		previousButton.setName("previousButton");
		previousButton.addActionListener(this);
		buttonPanel.add(previousButton);

		lastButton.setText("Last");
		lastButton.setName("lastButton");
		lastButton.addActionListener(this);
		buttonPanel.add(lastButton);

		findButton.setText("Find");
		findButton.setName("findButton");
		findButton.addActionListener(this);
		buttonPanel.add(findButton);

		addButton.setText("Add");
		addButton.setEnabled(true);
		addButton.setName("addButton");
		addButton.addActionListener(this);
		buttonPanel.add(addButton);

		deleteButton.setText("Delete");
		deleteButton.setEnabled(true);
		deleteButton.setName("deleteButton");
		deleteButton.addActionListener(this);
		buttonPanel.add(deleteButton);

		updateButton.setText("Update");
		updateButton.setEnabled(true);
		updateButton.setName("updateButton");
		updateButton.addActionListener(this);
		buttonPanel.add(updateButton);

		getContentPane().add(buttonPanel);

		getFiles();
		
		try {
			index = new RandomAccessFile(indexFile, "rw");
			book = new RandomAccessFile(bookFile, "rw");
		} catch(IOException ioe) {
			System.out.println(ioe);
			System.exit(0);
		}

	}
	
	void getFiles() {
			FileDialog fd = new FileDialog(this, "Select the Address Book", FileDialog.LOAD);
			fd.setVisible(true);
			String filename = fd.getFile();
			if (filename == null)
				System.exit(0);
			bookFile = fd.getDirectory() + filename;
			fd = new FileDialog(this, "Select the Index File", FileDialog.LOAD);
			fd.setVisible(true);
			filename = fd.getFile();
			if (filename == null)
				System.exit(0);
			indexFile = fd.getDirectory() + filename;
	}


	public static void main(String[] args) {
		Lab3 window = new Lab3();
		window.setVisible(true);
	}

/***************************************************************/

	public void actionPerformed(ActionEvent evt) {
//implement this method
//you may add additional methods as needed
//do not change any of the code that I have written
//other than to activate buttons for extra credit
		
		// CONDITIONAL STATEMENTS FOR BUTTONS
		try {
		if (evt.getActionCommand().equals("Find")) {	
				find(0,index.length()-8);
		}
		else if (evt.getActionCommand().equals("First")) {
				first();
		}
		else if (evt.getActionCommand().equals("Next")) {
				next();
		}else if (evt.getActionCommand().equals("Previous")) {
				previous();
		}else if (evt.getActionCommand().equals("Last")) {
				last();
		}
		else if (evt.getActionCommand().equals("Add")) {
//				add();
		}
		else if (evt.getActionCommand().equals("Update")) {
				update();
		}
		else if(evt.getActionCommand().equals("Delete")) {
//				delete();
		}
		}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	long initialOffset = -8; //GLOBAL VARIABLE
	
//METHODS FOR ALL THE BOTTONS	
	
public void find(long start, long end) throws IOException,EOFException {
	long mid = (start+end)/2;   //FINDING THE MID POINT
	mid = mid / 8 * 8;			//SINCE OFFSET WILL ALWAYS BE A MULTIPLE OF 8
	if (start>end) {            //BASE CASE
		initialOffset= start;
		index.seek(initialOffset);
		long offSet = index.readLong();
		book.seek(offSet);
		
		// READING AND DISPLAYING THE INFO
		
		String surname = book.readUTF();
		surnameTextField.setText(surname);  
		String givenName = book.readUTF();
		givenNameTextField.setText(givenName);
		String address = book.readUTF();
		addressTextField.setText(address);
		String city = book.readUTF();
		cityTextField.setText(city);
		String state = book.readUTF();
		stateTextField.setText(state);
		
		return;
	}
		
	index.seek(mid);
	long offsetIndex= index.readLong();
	book.seek(offsetIndex);
	//COLLECTING INFO FROM THE ADDRESS BOOK FILE
	String surname = book.readUTF();
	String givenName = book.readUTF();
	
	//COLLECTING INFO FROM THE TEXT FIELDS
	//what we are looking for
	String search = surnameTextField.getText()+" "+ givenNameTextField.getText();
	
	//INFO FROM THE ADDRESS BOOK
	String target = surname+" "+givenName; //what we've got
	
	int compare = target.compareToIgnoreCase(search);
	
	if (compare == 0) {			//TARGET IS SEARCH
		initialOffset=mid;
		// DISPLAYING THE INFO
		surnameTextField.setText(surname);
		givenNameTextField.setText(givenName);
		String address = book.readUTF();
		addressTextField.setText(address);
		String city = book.readUTF();
		cityTextField.setText(city);
		String state = book.readUTF();
		stateTextField.setText(state);
	}
	else if (compare < 0) {		//TARGET IS BEFORE SEARCH
		find(mid+8,end);		//SO WE SEARCH THE NEXT HALF
		
		
	}
	else if (compare > 0) {     // TARGET IS AFTER THE SEARCH
		find(start,mid-8);		// SO WE SEARCH THE PREVIOUS HALF
	}
}
public void first() throws IOException {
	initialOffset = 0;  // SETTING THE OFFSET TO 0, AS ITS THE FIRST OFFSET
	index.seek(0);
	long offset = index.readLong();
	book.seek(offset);
	// READING AND DISPLAYING THE INFO OF THE FIRST RECORD
	String surname = book.readUTF();
	surnameTextField.setText(surname);
	String givenName = book.readUTF();
	givenNameTextField.setText(givenName);
	String address = book.readUTF();
	addressTextField.setText(address);
	String city = book.readUTF();
	cityTextField.setText(city);
	String state = book.readUTF();
	stateTextField.setText(state);
}
public void next() throws IOException {
	long currentOffset = initialOffset + 8 ; // MOVING THE POINTER TO THE NEXT OFFSET
	initialOffset=initialOffset+8;
	index.seek(currentOffset);
	long nextOffset = index.readLong();
	book.seek(nextOffset);
	//READING AND DISPLAYING THE INFO OF THE NEXT RECORD 
	String surname = book.readUTF();
	surnameTextField.setText(surname);
	String givenName = book.readUTF();
	givenNameTextField.setText(givenName);
	String address = book.readUTF();
	addressTextField.setText(address);
	String city = book.readUTF();
	cityTextField.setText(city);
	String state = book.readUTF();
	stateTextField.setText(state);
	
}
public void previous() throws IOException {
	long currentOffset = initialOffset - 8 ; 
	initialOffset=initialOffset-8;   // MOVING THE POINTER TO THE PREVIOUS OFFSET
	if(currentOffset<0) {
		currentOffset=0;
	}
	index.seek(currentOffset);
	long nextOffset = index.readLong();
	book.seek(nextOffset);
	//READING AND DISPLAYING THE INFO OF THE PREVIOUS RECORD
	String surname = book.readUTF();
	surnameTextField.setText(surname);
	String givenName = book.readUTF();
	givenNameTextField.setText(givenName);
	String address = book.readUTF();
	addressTextField.setText(address);
	String city = book.readUTF();
	cityTextField.setText(city);
	String state = book.readUTF();
	stateTextField.setText(state);
}
public void last() throws IOException {
	initialOffset = index.length()-8; // SETTING THE OFFSET TO THE INDEX FILE'S LENGTH, AS ITS THE LAST OFFSET
	index.seek(initialOffset);
	long offset = index.readLong();
	book.seek(offset);
	//READING AND DISPLAYING THE INFO OF THE LAST RECORD
	String surname = book.readUTF();
	surnameTextField.setText(surname);
	String givenName = book.readUTF();
	givenNameTextField.setText(givenName);
	String address = book.readUTF();
	addressTextField.setText(address);
	String city = book.readUTF();
	cityTextField.setText(city);
	String state = book.readUTF();
	stateTextField.setText(state);
}
public void update () throws IOException {
	// COLLECTING THE NEW INFO FOR THE RECORD
	String surname = surnameTextField.getText();
	String givenName = givenNameTextField.getText();
	String address = addressTextField.getText();
	String city = cityTextField.getText();
	String state = stateTextField.getText();
	
	String targetSearch = surname+" "+givenName;
	
	find(0,index.length()-8);  //FINDING THAT RECORD
    index.seek(initialOffset);  //MOVING THE POINTER TO THAT OFFSET
    long targetOffset = book.length(); 
    index.writeLong(targetOffset);
 
    book.seek(targetOffset);
    // UPDATING/WRINTING THE INFO
    book.writeUTF(surname);
    book.writeUTF(givenName);
    book.writeUTF(address);
    book.writeUTF(city);
    book.writeUTF(state);
    // DISPLAYING THE UPDATED TEXT
    addressTextField.setText(address);
    cityTextField.setText(city);
    stateTextField.setText(state);
}
}


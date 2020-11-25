// *https://www.registers.service.gov.uk/registers/country/use-the-api*
import fetch from 'cross-fetch';
import React, { useState, useRef } from "react";
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import CircularProgress from '@material-ui/core/CircularProgress';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import { DataGrid } from '@material-ui/data-grid';
import BarChart from "./BarChart.js"
import LinearProgress from '@material-ui/core/LinearProgress';
import MovieInfoTable from "./MovieInfoTable.js";

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
}));
export default function Asynchronous() {

const columns = [
  { field: 'id', headerName: 'ID', width: "150px" }, 
  { field: 'movie', headerName: 'Movie', width: "380px" }
];

  
  const movieArray = [
    "Avatar",
    "Pirates of the Caribbean: At World's End",
    "Spectre",
    "The Dark Knight Rises",
    "Star Wars: Episode VII - The Force Awakens",            
    "John Carter",
    "Spider-Man 3",
    "Tangled",
    "Avengers: Age of Ultron",
    "Harry Potter and the Half-Blood Prince",
    "Batman v Superman: Dawn of Justice",
    "Superman Returns",
    "Quantum of Solace",
    "Pirates of the Caribbean: Dead Man's Chest",
    "The Lone Ranger",
    "Man of Steel",
    "The Chronicles of Narnia: Prince Caspian",
    "The Avengers",
    "Pirates of the Caribbean: On Stranger Tides",
    "Men in Black 3",
    "The Hobbit: The Battle of the Five Armies",
    "The Amazing Spider-Man",
    "Robin Hood",
    "The Hobbit: The Desolation of Smaug",
    "The Golden Compass",
    "King Kong",
    "Titanic",
    "Captain America: Civil War",
    "Battleship",
    "Jurassic World",
    "Skyfall",
    "Spider-Man 2",
    "Iron Man 3",
    "Alice in Wonderland",
    "X-Men: The Last Stand",
    "Monsters University",
    "Transformers: Revenge of the Fallen",
    "Transformers: Age of Extinction",
    "Oz the Great and Powerful",
    "The Amazing Spider-Man 2",
    "TRON: Legacy",
    "Cars 2",
    "Green Lantern",
    "Toy Story 3",
    "Terminator Salvation",
    "Furious 7",
    "World War Z",
    "X-Men: Days of Future Past",
    "Star Trek Into Darkness"
    ];

    const ratingArray = [
    "1","2","3","4","5","6","7","8","9","10"
    ]
  const directorArray = [
"James Cameron",
"Gore Verbinski",
"Sam Mendes",
"Christopher Nolan",
"Sam Raimi",
"Andrew Stanton",
"Sam Raimi",
"Nathan Greno",
"Joss Whedon",
"David Yates",
"Zack Snyder",
"Bryan Singer",
"Marc Forster",
"Andrew Adamson",
"Rob Marshall",
"Barry Sonnenfeld",
"Peter Jackson",
"Marc Webb",
"Ridley Scott",
"Chris Weitz",
"Anthony Russo",
"Peter Berg",
"Colin Trevorrow",
"Shane Black",
"Tim Burton",
"Brett Ratner",
"Dan Scanlon",
"Michael Bay",
"Joseph Kosinski",
"John Lasseter",
"Martin Campbell",
"Lee Unkrich",
"McG",
"James Wan",
"J.J. Abrams",
"Baz Luhrmann",
"Mike Newell",
"Guillermo del Toro",
"Steven Spielberg",
"Peter Sohn",
"Mark Andrews",
"Justin Lin",
"Roland Emmerich",
"Robert Zemeckis",
"Lana Wachowski",
"Pete Docter",
"Rob Letterman",
"Jon Favreau",
"Martin Scorsese"
  ]
  const classes = useStyles();
  let textInput = useRef(null);
  const [value, setValue] = React.useState('movieName');
  const [optionSelected, setOptionSelected] = React.useState('Movie Name');
  const [open, setOpen] = React.useState(false);
  const [options, setOptions] = React.useState(movieArray);
  const [searchText, setSearchText] = React.useState('');
  const loading = open && options.length === 0;
  const [tableHidden, setTableHidden] = React.useState(true);
  const [rows, setrows] = React.useState([]);
  const [loadingSymbol, setloadingSymbol] = React.useState(false);
  const [buttonHidden, setButtonHidden] = React.useState(true);
  const [graphData, setGraphData] = React.useState([]);
  const [graphHidden, setGraphHidden] = React.useState(false);
  const [movieInfoHidden, setmovieInfoHidden] = React.useState(true);
  const [tableData, setTableData] = React.useState([]);
  const axios = require("axios");

  const sendRequest = (url, parse=false) => {
    setloadingSymbol(true);
    console.log(url);
    axios.get(url)
    .then(response => {
      console.log(response);
      if(parse === 2) {
        response.data.data.map( (dataResponse) => {
          dataResponse.movie = dataResponse.movie.substring(0,dataResponse.movie.length-2);
          console.log(dataResponse.movie);
        })
        setrows(response.data.data);
        setGraphData(response.data.graph);
        setButtonHidden(false);
      }else if(parse === 1) {
        response.data.map( (dataResponse) => {
          dataResponse.movie = dataResponse.movie.substring(0,dataResponse.movie.length-2);
          console.log(dataResponse.movie);
        })
        setrows(response.data);
      } else if(parse === 3){
        response.data.title.substring(0,response.data.title.length-2)
        setTableData(response.data);
        setTableHidden(true);
        setmovieInfoHidden(false);
      }
      setloadingSymbol(false);
    });
  }

  const displayGraph = () => {
    setTableHidden(true);
    setGraphHidden(false);
  }

  const handleSearch = (event ,value) => {
    if(value != null) {
    if(optionSelected === "Director"){
      sendRequest('http://localhost:8080/getDirectorMovieInfo?name="'+value+'"', 1)
    }
    else if(optionSelected === "Minimum Rating"){
      sendRequest('http://localhost:8080/getMovieWithMinRating?rating='+value, 2)
    } 
    else if(optionSelected === "Movie Name") {
      sendRequest('http://localhost:8080/getMovieInfo?name="'+value+'"', 3)
      
    }
  }
    console.log(value);
    setSearchText(value);
    if(value === null){
      setTableHidden(true);
      setButtonHidden(true);
      setGraphHidden(true);
      setmovieInfoHidden(true);
    }else {
      setTableHidden(false)
    }
  }

  const clearAll = () => {
    setSearchText(null);
    setTableHidden(true);
    setGraphHidden(true);
    setmovieInfoHidden(true);
    setButtonHidden(true);
  }
  const handleChange = (event) => {
    setValue(event.target.value);

    if(event.target.value === "movieName"){
      setOptionSelected("Movie Name");
      setOptions(movieArray);
    }
    else if(event.target.value === "director"){
      setOptionSelected("Director");
      setOptions(directorArray);
    }
    else if(event.target.value === "rating"){
      setOptionSelected("Minimum Rating");
      setOptions(ratingArray);
    }
    clearAll();
  };

  return (
    <div>
      <AppBar position="static">
        <Toolbar>
          
        <Typography variant="h6" className={classes.title}>
            Movie Reccommendation System
          </Typography>
          
        </Toolbar>
      </AppBar>
      <div style={{marginTop:"20px"}}>

      <FormControl component="fieldset">
      <FormLabel component="legend">Selection one option </FormLabel>
      <RadioGroup aria-label="Select One Option" name="searchoption" value={value} onChange={handleChange} row>
        <FormControlLabel value="movieName" control={<Radio />} label="Movie Name" />
        <FormControlLabel value="director" control={<Radio />} label="Director" />
        <FormControlLabel value="rating" control={<Radio />} label="Minimum Rating" />
      </RadioGroup>
    </FormControl>
      <Autocomplete
     
        id="asynchronous-demo"
        style={{ width: 400 }}
        open={open}
        onOpen={() => {
          setOpen(true);
        }}
        onClose={() => {
          setOpen(false);
        }}
        value={searchText}
        onChange={handleSearch}
        getOptionSelected={(option, value) => option.name === value.name}
        getOptionLabel={(option) => option}
        options={options}
        loading={loading}
        renderInput={(params) => (
          <TextField
          
          inputRef={textInput}
          style={{ 
            margin: "10px 555px"}}
            {...params}
            label={"Search by "+optionSelected}
            variant="outlined"
            InputProps={{
              ...params.InputProps,
              endAdornment: (
                <React.Fragment>
                  {loading ? <CircularProgress color="inherit" size={20} /> : null}
                  {params.InputProps.endAdornment}
                </React.Fragment>     
              ),
            }}
          />
        )}
      />
      <div hidden={buttonHidden} >
      <Button variant="contained" color="primary" onClick={displayGraph}>
  Visualize Ratings
</Button>
</div>
<div style={{ height: 400,  margin:"50px 100px 0 475px", overflow:"hidden"}} hidden={movieInfoHidden}>
      {loadingSymbol ? <LinearProgress /> : 
        <div>  
        <table border="1">
          <tr>
            <th>Property</th>
            <th>Value</th>
          </tr>
          <tr>
            <td>Country</td>
            <td>{tableData.country}</td>
          </tr>
          <tr>
            <td>IMDB Link</td>
            <td><a href={tableData.imdblink}>{tableData.imdblink}</a></td>
          </tr>
          <tr>
            <td>Number of Users</td>
            <td>{tableData.numusers}</td>
          </tr>
          <tr>
            <td>Director</td>
            <td>{tableData.director}</td>
          </tr>
          <tr>
            <td>Language</td>
            <td>{tableData.language}</td>
          </tr>
          <tr>
            <td>Title</td>
            <td>{tableData.title}</td>
          </tr>
          <tr>
            <td>IMDB Score</td>
            <td>{tableData.imdbscore}</td>
          </tr>
          <tr>
            <td>Duration</td>
            <td>{tableData.duration}</td>
          </tr>
          <tr>
            <td>Actor 1</td>
            <td>{tableData.actor1}</td>
          </tr>
          <tr>
            <td>Actor 2</td>
            <td>{tableData.actor2}</td>
          </tr>
          <tr>
            <td>Actor 3</td>
            <td>{tableData.actor3}</td>
          </tr>
          <tr>
            <td>Genre</td>
            <td>{tableData.genre}</td>
          </tr>
          <tr>
            <td>Number of Users Voted</td>
            <td>{tableData.numvoted}</td>
          </tr>
        </table>
        
              </div>
        }
    </div>      

    {
      
    }    
    <div style={{ height: 475, width: '35%',  margin:"10px 100px 0 470px", textAlign:"left" }} hidden={tableHidden}>
      {loadingSymbol ? <LinearProgress /> : <DataGrid rows={rows} columns={columns} pageSize={7} /> }
    </div>
      </div>
      <div style={{ margin:"50px 100px 0 450px"}}  hidden={graphHidden}>
      <BarChart data={graphData}/>
      </div>

    </div>

  );
}
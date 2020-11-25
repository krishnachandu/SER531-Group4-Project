import React, { Component } from "react";
import Search from "./Search.js"
class MovieInfoTable extends Component {
  render() {
      console.log(this.props.data);
    return (
      <div>  
<table>
  <tr>
    <th>Country</th>
    <th>Value</th>
  </tr>
  <tr>
    <td>IMDB Link</td>
    <td>Maria Anders</td>
  </tr>
  <tr>
    <td>Number of Users</td>
    <td>Francisco Chang</td>
  </tr>
  <tr>
    <td>Director</td>
    <td>Roland Mendel</td>
  </tr>
  <tr>
    <td>Language</td>
    <td>Helen Bennett</td>
  </tr>
  <tr>
    <td>Title</td>
    <td>Yoshi Tannamuri</td>
  </tr>
  <tr>
    <td>IMDB Score</td>
    <td>Giovanni Rovelli</td>
  </tr>
  <tr>
    <td>Duration</td>
    <td>Giovanni Rovelli</td>
  </tr>
  <tr>
    <td>Actor 1</td>
    <td>Giovanni Rovelli</td>
  </tr>
  <tr>
    <td>Actor 2</td>
    <td>Giovanni Rovelli</td>
  </tr>
  <tr>
    <td>Actor 3</td>
    <td>Giovanni Rovelli</td>
  </tr>
  <tr>
    <td>Genre</td>
    <td>Giovanni Rovelli</td>
  </tr>
  <tr>
    <td>Number of Users Voted</td>
    <td>Giovanni Rovelli</td>
  </tr>
</table>

      </div>
    );
  }
}

export default MovieInfoTable;

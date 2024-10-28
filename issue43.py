import dash
from dash import dcc, html
import pandas as pd
import plotly.express as px

data = {
    'Time': ['08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00'],
    'Cars': [120, 150, 170, 180, 160, 140, 130, 110],
    'Bikes': [30, 40, 50, 60, 55, 50, 45, 40],
    'Buses': [10, 15, 20, 25, 20, 15, 10, 5]
}

df = pd.DataFrame(data)

app = dash.Dash(__name__)

fig = px.line(df, x='Time', y=['Cars', 'Bikes', 'Buses'], 
              labels={'value': 'Number of Vehicles', 'variable': 'Vehicle Type'},
              title='Traffic Volume Over Time')

app.layout = html.Div(children=[
    html.H1(children='Traffic Dashboard'),
    html.Div(children='''Traffic data visualized over time.'''),
    dcc.Graph(
        id='traffic-graph',
        figure=fig
    )
])

if __name__ == '__main__':
    app.run_server(debug=True)

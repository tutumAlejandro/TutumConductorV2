/*try{
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put("name",userName);
        jsonObject.put("password", userPass);
        jsonObject.put("phone", userPhone);
        jsonObject.put("email",userEmail);
        jsonObject.put("api_token", userToken);

         final String requestBody = jsonObject.toString();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                 Log.d("TAG", "Success! :D" + " " + response);
                 try {
                       isSucess = response.getBoolean("success");
                       if(isSucess){
                           String getMsg = response.getString("msg");
                           Toast.makeText(context, getMsg, Toast.LENGTH_SHORT).show();
                           Log.d("SUCCESS", "Cambios guardados");
                       }
                       else{
                           String getMsg = response.getString("msg");
                           Toast.makeText(context, getMsg, Toast.LENGTH_SHORT).show();
                       }
                     } catch (JSONException e) {
                        Log.e("ERROR", "Hubo un error al guardar los cambios");
                     }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 error.printStackTrace();
                 Log.d("TAG", "Error: " + error);
                 if(String.valueOf(error).contains("com.android.volley.AuthFailureError"))
                 {
                     SharedPreferences sharedPreferences = context.getSharedPreferences("isLogged", Context.MODE_PRIVATE);
                     Toast.makeText(context, "Hubo un error al obetener los datos del usuario, debes iniciar sesion de nuevo", Toast.LENGTH_SHORT).show();
                     SharedPreferences.Editor editor = sharedPreferences.edit();
                     editor.putString("isLogged", "NO");
                     editor.apply();
                     Intent intent = new Intent(context, MainActivity.class);
                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     context.startActivity(intent);
                 }
            }
          });
        {
          @Override
          public Map<String, String> getHeaders() throws AuthFailureError {
          Map<String, String> params = new HashMap<String, String>();
          params.put("Accept", "application/json");
          return params;
          }
        };
        requestQueue.add(jsonObjectRequest);
        }
        catch (JSONException e){
        e.printStackTrace();
        }
}*/
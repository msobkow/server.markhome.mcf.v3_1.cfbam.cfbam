// Description: Java 25 Table Object interface for CFBam.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamTZDateDefTableObj
{
	public ICFBamSchemaObj getSchema();
	public void setSchema( ICFBamSchemaObj value );

	public void minimizeMemory();

	public String getTableName();
	public String getTableDbName();

	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	public int getClassCode();

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	// public static int getBackingClassCode();

	Class getObjQualifyingClass();

	/**
	 *	Instantiate a new TZDateDef instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamTZDateDefObj newInstance();

	/**
	 *	Instantiate a new TZDateDef edition of the specified TZDateDef instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamTZDateDefEditObj newEditInstance( ICFBamTZDateDefObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamTZDateDefObj realiseTZDateDef( ICFBamTZDateDefObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamTZDateDefObj createTZDateDef( ICFBamTZDateDefObj Obj );

	/**
	 *	Read a TZDateDef-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TZDateDef-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTZDateDefObj readTZDateDef( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a TZDateDef-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TZDateDef-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTZDateDefObj readTZDateDef( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamTZDateDefObj readCachedTZDateDef( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeTZDateDef( ICFBamTZDateDefObj obj );

	void deepDisposeTZDateDef( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamTZDateDefObj lockTZDateDef( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the TZDateDef-derived instances in the database.
	 *
	 *	@return	List of ICFBamTZDateDefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTZDateDefObj> readAllTZDateDef();

	/**
	 *	Return a sorted map of all the TZDateDef-derived instances in the database.
	 *
	 *	@return	List of ICFBamTZDateDefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTZDateDefObj> readAllTZDateDef( boolean forceRead );

	List<ICFBamTZDateDefObj> readCachedAllTZDateDef();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTZDateDefObj readTZDateDefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTZDateDefObj readTZDateDefByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTZDateDefObj readTZDateDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTZDateDefObj readTZDateDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateDefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateDefObj> readTZDateDefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamTZDateDefObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateDefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateDefObj> readTZDateDefByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateDefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateDefObj> readTZDateDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamTZDateDefObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateDefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateDefObj> readTZDateDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateDefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateDefObj> readTZDateDefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTZDateDefObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateDefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateDefObj> readTZDateDefByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateDefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateDefObj> readTZDateDefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTZDateDefObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateDefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateDefObj> readTZDateDefByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateDefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateDefObj> readTZDateDefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTZDateDefObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateDefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateDefObj> readTZDateDefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateDefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateDefObj> readTZDateDefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTZDateDefObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateDefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateDefObj> readTZDateDefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	ICFBamTZDateDefObj readCachedTZDateDefByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamTZDateDefObj readCachedTZDateDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamTZDateDefObj> readCachedTZDateDefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamTZDateDefObj> readCachedTZDateDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamTZDateDefObj> readCachedTZDateDefByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamTZDateDefObj> readCachedTZDateDefByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamTZDateDefObj> readCachedTZDateDefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamTZDateDefObj> readCachedTZDateDefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeTZDateDefByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeTZDateDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeTZDateDefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeTZDateDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeTZDateDefByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeTZDateDefByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeTZDateDefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeTZDateDefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamTZDateDefObj updateTZDateDef( ICFBamTZDateDefObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteTZDateDef( ICFBamTZDateDefObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The TZDateDef key attribute of the instance generating the id.
	 */
	void deleteTZDateDefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TZDateDef key attribute of the instance generating the id.
	 */
	void deleteTZDateDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TZDateDef key attribute of the instance generating the id.
	 */
	void deleteTZDateDefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The TZDateDef key attribute of the instance generating the id.
	 */
	void deleteTZDateDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The TZDateDef key attribute of the instance generating the id.
	 */
	void deleteTZDateDefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The TZDateDef key attribute of the instance generating the id.
	 */
	void deleteTZDateDefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TZDateDef key attribute of the instance generating the id.
	 */
	void deleteTZDateDefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TZDateDef key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TZDateDef key attribute of the instance generating the id.
	 */
	void deleteTZDateDefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Move the CFBamTZDateDefObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTZDateDefObj refreshed cache instance.
	 */
	ICFBamTZDateDefObj moveUpTZDateDef( ICFBamTZDateDefObj Obj );

	/**
	 *	Move the CFBamTZDateDefObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTZDateDefObj refreshed cache instance.
	 */
	ICFBamTZDateDefObj moveDownTZDateDef( ICFBamTZDateDefObj Obj );
}

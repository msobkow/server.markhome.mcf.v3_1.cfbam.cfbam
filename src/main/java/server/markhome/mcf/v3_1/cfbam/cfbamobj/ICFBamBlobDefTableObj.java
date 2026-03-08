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

public interface ICFBamBlobDefTableObj
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
	 *	Instantiate a new BlobDef instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamBlobDefObj newInstance();

	/**
	 *	Instantiate a new BlobDef edition of the specified BlobDef instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamBlobDefEditObj newEditInstance( ICFBamBlobDefObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamBlobDefObj realiseBlobDef( ICFBamBlobDefObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamBlobDefObj createBlobDef( ICFBamBlobDefObj Obj );

	/**
	 *	Read a BlobDef-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The BlobDef-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamBlobDefObj readBlobDef( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a BlobDef-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The BlobDef-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamBlobDefObj readBlobDef( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamBlobDefObj readCachedBlobDef( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeBlobDef( ICFBamBlobDefObj obj );

	void deepDisposeBlobDef( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamBlobDefObj lockBlobDef( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the BlobDef-derived instances in the database.
	 *
	 *	@return	List of ICFBamBlobDefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamBlobDefObj> readAllBlobDef();

	/**
	 *	Return a sorted map of all the BlobDef-derived instances in the database.
	 *
	 *	@return	List of ICFBamBlobDefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamBlobDefObj> readAllBlobDef( boolean forceRead );

	List<ICFBamBlobDefObj> readCachedAllBlobDef();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamBlobDefObj readBlobDefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamBlobDefObj readBlobDefByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamBlobDefObj readBlobDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamBlobDefObj readBlobDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobDefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobDefObj> readBlobDefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamBlobDefObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobDefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobDefObj> readBlobDefByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobDefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobDefObj> readBlobDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamBlobDefObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobDefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobDefObj> readBlobDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobDefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobDefObj> readBlobDefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamBlobDefObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobDefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobDefObj> readBlobDefByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobDefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobDefObj> readBlobDefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamBlobDefObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobDefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobDefObj> readBlobDefByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobDefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobDefObj> readBlobDefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamBlobDefObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobDefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobDefObj> readBlobDefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobDefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobDefObj> readBlobDefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamBlobDefObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBlobDefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBlobDefObj> readBlobDefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	ICFBamBlobDefObj readCachedBlobDefByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamBlobDefObj readCachedBlobDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamBlobDefObj> readCachedBlobDefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamBlobDefObj> readCachedBlobDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamBlobDefObj> readCachedBlobDefByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamBlobDefObj> readCachedBlobDefByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamBlobDefObj> readCachedBlobDefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamBlobDefObj> readCachedBlobDefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeBlobDefByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeBlobDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeBlobDefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeBlobDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeBlobDefByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeBlobDefByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeBlobDefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeBlobDefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamBlobDefObj updateBlobDef( ICFBamBlobDefObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteBlobDef( ICFBamBlobDefObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The BlobDef key attribute of the instance generating the id.
	 */
	void deleteBlobDefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The BlobDef key attribute of the instance generating the id.
	 */
	void deleteBlobDefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BlobDef key attribute of the instance generating the id.
	 */
	void deleteBlobDefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The BlobDef key attribute of the instance generating the id.
	 */
	void deleteBlobDefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The BlobDef key attribute of the instance generating the id.
	 */
	void deleteBlobDefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The BlobDef key attribute of the instance generating the id.
	 */
	void deleteBlobDefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The BlobDef key attribute of the instance generating the id.
	 */
	void deleteBlobDefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BlobDef key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The BlobDef key attribute of the instance generating the id.
	 */
	void deleteBlobDefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Move the CFBamBlobDefObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamBlobDefObj refreshed cache instance.
	 */
	ICFBamBlobDefObj moveUpBlobDef( ICFBamBlobDefObj Obj );

	/**
	 *	Move the CFBamBlobDefObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamBlobDefObj refreshed cache instance.
	 */
	ICFBamBlobDefObj moveDownBlobDef( ICFBamBlobDefObj Obj );
}

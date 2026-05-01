// Description: Java 25 Table Object implementation for SecRole.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
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
import server.markhome.mcf.v3_1.cfsec.cfsec.*;

public class CFBamSecRoleTableObj
	implements ICFBamSecRoleTableObj
{
	protected ICFBamSchemaObj schema;
	private Map<CFLibDbKeyHash256, ICFSecSecRoleObj> members;
	private Map<CFLibDbKeyHash256, ICFSecSecRoleObj> allSecRole;
	private Map< ICFSecSecRoleByUNameIdxKey,
		ICFSecSecRoleObj > indexByUNameIdx;
	public static String TABLE_NAME = "SecRole";
	public static String TABLE_DBNAME = "secrole";

	public CFBamSecRoleTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFSecSecRoleObj>();
		allSecRole = null;
		indexByUNameIdx = null;
	}

	public CFBamSecRoleTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFSecSecRoleObj>();
		allSecRole = null;
		indexByUNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecRoleTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( CFSecSecRoleTableObj.getBackingClassCode() );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( CFSecSecRoleTableObj.getRuntimeClassCode() );
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( null );
	}


	@Override
	public void minimizeMemory() {
		allSecRole = null;
		indexByUNameIdx = null;
		List<ICFSecSecRoleObj> toForget = new LinkedList<ICFSecSecRoleObj>();
		ICFSecSecRoleObj cur = null;
		Iterator<ICFSecSecRoleObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecRoleObj.
	 */
	@Override
	public ICFSecSecRoleObj newInstance() {
		ICFSecSecRoleObj inst = new CFBamSecRoleObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecRoleObj.
	 */
	@Override
	public ICFSecSecRoleEditObj newEditInstance( ICFSecSecRoleObj orig ) {
		ICFSecSecRoleEditObj edit = new CFBamSecRoleEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecRoleObj realiseSecRole( ICFSecSecRoleObj Obj ) {
		ICFSecSecRoleObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFSecSecRoleObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecRoleObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUNameIdx != null ) {
				ICFSecSecRoleByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getFactorySecRole().newByUNameIdxKey();
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUNameIdx != null ) {
				ICFSecSecRoleByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getFactorySecRole().newByUNameIdxKey();
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( allSecRole != null ) {
				allSecRole.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecRole != null ) {
				allSecRole.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUNameIdx != null ) {
				ICFSecSecRoleByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getFactorySecRole().newByUNameIdxKey();
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecRoleObj createSecRole( ICFSecSecRoleObj Obj ) {
		ICFSecSecRoleObj obj = Obj;
		ICFSecSecRole rec = obj.getSecRoleRec();
		schema.getCFSecBackingStore().getTableSecRole().createSecRole(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecRoleObj readSecRole( CFLibDbKeyHash256 pkey ) {
		return( readSecRole( pkey, false ) );
	}

	@Override
	public ICFSecSecRoleObj readSecRole( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFSecSecRoleObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecRole readRec = schema.getCFSecBackingStore().getTableSecRole().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getSecRoleTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecRoleObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecRoleObj readCachedSecRole( CFLibDbKeyHash256 pkey ) {
		ICFSecSecRoleObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecRole( ICFSecSecRoleObj obj )
	{
		final String S_ProcName = "CFBamSecRoleTableObj.reallyDeepDisposeSecRole() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFSecSecRoleObj existing = readCachedSecRole( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecRoleByUNameIdxKey keyUNameIdx = schema.getCFSecBackingStore().getFactorySecRole().newByUNameIdxKey();
		keyUNameIdx.setRequiredName( existing.getRequiredName() );


					schema.getSecRoleMembTableObj().deepDisposeSecRoleMembByRoleIdx( existing.getRequiredSecRoleId() );
					schema.getSecRoleEnablesTableObj().deepDisposeSecRoleEnablesByRoleIdx( existing.getRequiredSecRoleId() );

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}


	}
	@Override
	public void deepDisposeSecRole( CFLibDbKeyHash256 pkey ) {
		ICFSecSecRoleObj obj = readCachedSecRole( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecRoleObj lockSecRole( CFLibDbKeyHash256 pkey ) {
		ICFSecSecRoleObj locked = null;
		ICFSecSecRole lockRec = schema.getCFSecBackingStore().getTableSecRole().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecRoleTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecRoleObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecRole", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecRoleObj> readAllSecRole() {
		return( readAllSecRole( false ) );
	}

	@Override
	public List<ICFSecSecRoleObj> readAllSecRole( boolean forceRead ) {
		final String S_ProcName = "readAllSecRole";
		if( ( allSecRole == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFSecSecRoleObj> map = new HashMap<CFLibDbKeyHash256,ICFSecSecRoleObj>();
			allSecRole = map;
			ICFSecSecRole[] recList = schema.getCFSecBackingStore().getTableSecRole().readAllDerived( null );
			ICFSecSecRole rec;
			ICFSecSecRoleObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecRoleObj realised = (ICFSecSecRoleObj)obj.realise();
			}
		}
		int len = allSecRole.size();
		ICFSecSecRoleObj arr[] = new ICFSecSecRoleObj[len];
		Iterator<ICFSecSecRoleObj> valIter = allSecRole.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecRoleObj> arrayList = new ArrayList<ICFSecSecRoleObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecRoleObj> cmp = new Comparator<ICFSecSecRoleObj>() {
			@Override
			public int compare( ICFSecSecRoleObj lhs, ICFSecSecRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecRoleObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecRoleObj> readCachedAllSecRole() {
		final String S_ProcName = "readCachedAllSecRole";
		ArrayList<ICFSecSecRoleObj> arrayList = new ArrayList<ICFSecSecRoleObj>();
		if( allSecRole != null ) {
			int len = allSecRole.size();
			ICFSecSecRoleObj arr[] = new ICFSecSecRoleObj[len];
			Iterator<ICFSecSecRoleObj> valIter = allSecRole.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFSecSecRoleObj> cmp = new Comparator<ICFSecSecRoleObj>() {
			public int compare( ICFSecSecRoleObj lhs, ICFSecSecRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFSecSecRoleObj readSecRoleByIdIdx( CFLibDbKeyHash256 SecRoleId )
	{
		return( readSecRoleByIdIdx( SecRoleId,
			false ) );
	}

	@Override
	public ICFSecSecRoleObj readSecRoleByIdIdx( CFLibDbKeyHash256 SecRoleId, boolean forceRead )
	{
		ICFSecSecRoleObj obj = readSecRole( SecRoleId, forceRead );
		return( obj );
	}

	@Override
	public ICFSecSecRoleObj readSecRoleByUNameIdx( String Name )
	{
		return( readSecRoleByUNameIdx( Name,
			false ) );
	}

	@Override
	public ICFSecSecRoleObj readSecRoleByUNameIdx( String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecSecRoleByUNameIdxKey,
				ICFSecSecRoleObj >();
		}
		ICFSecSecRoleByUNameIdxKey key = schema.getCFSecBackingStore().getFactorySecRole().newByUNameIdxKey();
		key.setRequiredName( Name );
		ICFSecSecRoleObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFSecSecRole rec = schema.getCFSecBackingStore().getTableSecRole().readDerivedByUNameIdx( null,
				Name );
			if( rec != null ) {
				obj = schema.getSecRoleTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecSecRoleObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecRoleObj readCachedSecRoleByIdIdx( CFLibDbKeyHash256 SecRoleId )
	{
		ICFSecSecRoleObj obj = null;
		obj = readCachedSecRole( SecRoleId );
		return( obj );
	}

	@Override
	public ICFSecSecRoleObj readCachedSecRoleByUNameIdx( String Name )
	{
		ICFSecSecRoleObj obj = null;
		ICFSecSecRoleByUNameIdxKey key = schema.getCFSecBackingStore().getFactorySecRole().newByUNameIdxKey();
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFSecSecRoleObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFSecSecRoleObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public void deepDisposeSecRoleByIdIdx( CFLibDbKeyHash256 SecRoleId )
	{
		ICFSecSecRoleObj obj = readCachedSecRoleByIdIdx( SecRoleId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecRoleByUNameIdx( String Name )
	{
		ICFSecSecRoleObj obj = readCachedSecRoleByUNameIdx( Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecRoleObj updateSecRole( ICFSecSecRoleObj Obj ) {
		ICFSecSecRoleObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecRole().updateSecRole( null,
			Obj.getSecRoleRec() );
		obj = (ICFSecSecRoleObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecRole( ICFSecSecRoleObj Obj ) {
		ICFSecSecRoleObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecRole().deleteSecRole( null,
			obj.getSecRoleRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecRoleByIdIdx( CFLibDbKeyHash256 SecRoleId )
	{
		ICFSecSecRoleObj obj = readSecRole(SecRoleId);
		if( obj != null ) {
			ICFSecSecRoleEditObj editObj = (ICFSecSecRoleEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecRoleEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeSecRoleByIdIdx( SecRoleId );
	}

	@Override
	public void deleteSecRoleByUNameIdx( String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecSecRoleByUNameIdxKey,
				ICFSecSecRoleObj >();
		}
		ICFSecSecRoleByUNameIdxKey key = schema.getCFSecBackingStore().getFactorySecRole().newByUNameIdxKey();
		key.setRequiredName( Name );
		ICFSecSecRoleObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecRole().deleteSecRoleByUNameIdx( null,
				Name );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableSecRole().deleteSecRoleByUNameIdx( null,
				Name );
		}
		deepDisposeSecRoleByUNameIdx( Name );
	}
}
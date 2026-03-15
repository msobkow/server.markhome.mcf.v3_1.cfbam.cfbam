// Description: Java 25 Table Object implementation for PopDep.

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
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamPopDepTableObj
	implements ICFBamPopDepTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamPopDep.CLASS_CODE;
	protected static final int backingClassCode = ICFBamPopDep.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamPopDepObj> members;
	private Map<CFLibDbKeyHash256, ICFBamPopDepObj> allPopDep;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamPopDepObj > > indexByTenantIdx;
	private Map< ICFBamPopDepByRelationIdxKey,
		Map<CFLibDbKeyHash256, ICFBamPopDepObj > > indexByRelationIdx;
	private Map< ICFBamPopDepByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamPopDepObj > > indexByDefSchemaIdx;
	public static String TABLE_NAME = "PopDep";
	public static String TABLE_DBNAME = "pop_dep";

	public CFBamPopDepTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamPopDepObj>();
		allPopDep = null;
		indexByTenantIdx = null;
		indexByRelationIdx = null;
		indexByDefSchemaIdx = null;
	}

	public CFBamPopDepTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamPopDepObj>();
		allPopDep = null;
		indexByTenantIdx = null;
		indexByRelationIdx = null;
		indexByDefSchemaIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamPopDepTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamPopDepTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
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
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allPopDep = null;
		indexByTenantIdx = null;
		indexByRelationIdx = null;
		indexByDefSchemaIdx = null;
		List<ICFBamPopDepObj> toForget = new LinkedList<ICFBamPopDepObj>();
		ICFBamPopDepObj cur = null;
		Iterator<ICFBamPopDepObj> iter = members.values().iterator();
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
	 *	CFBamPopDepObj.
	 */
	@Override
	public ICFBamPopDepObj newInstance() {
		ICFBamPopDepObj inst = new CFBamPopDepObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamPopDepObj.
	 */
	@Override
	public ICFBamPopDepEditObj newEditInstance( ICFBamPopDepObj orig ) {
		ICFBamPopDepEditObj edit = new CFBamPopDepEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamPopDepObj realisePopDep( ICFBamPopDepObj Obj ) {
		ICFBamPopDepObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamPopDepObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamPopDepObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamPopDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByRelationIdx != null ) {
				ICFBamPopDepByRelationIdxKey keyRelationIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
				keyRelationIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamPopDepObj > mapRelationIdx = indexByRelationIdx.get( keyRelationIdx );
				if( mapRelationIdx != null ) {
					mapRelationIdx.remove( keepObj.getPKey() );
					if( mapRelationIdx.size() <= 0 ) {
						indexByRelationIdx.remove( keyRelationIdx );
					}
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamPopDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamPopDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.remove( keepObj.getPKey() );
					if( mapDefSchemaIdx.size() <= 0 ) {
						indexByDefSchemaIdx.remove( keyDefSchemaIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamPopDepObj)schema.getScopeTableObj().realiseScope( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamPopDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRelationIdx != null ) {
				ICFBamPopDepByRelationIdxKey keyRelationIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
				keyRelationIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamPopDepObj > mapRelationIdx = indexByRelationIdx.get( keyRelationIdx );
				if( mapRelationIdx != null ) {
					mapRelationIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamPopDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamPopDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allPopDep != null ) {
				allPopDep.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamPopDepObj)schema.getScopeTableObj().realiseScope( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allPopDep != null ) {
				allPopDep.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamPopDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRelationIdx != null ) {
				ICFBamPopDepByRelationIdxKey keyRelationIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
				keyRelationIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamPopDepObj > mapRelationIdx = indexByRelationIdx.get( keyRelationIdx );
				if( mapRelationIdx != null ) {
					mapRelationIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamPopDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamPopDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamPopDepObj createPopDep( ICFBamPopDepObj Obj ) {
		ICFBamPopDepObj obj = Obj;
		ICFBamPopDep rec = obj.getPopDepRec();
		schema.getCFBamBackingStore().getTablePopDep().createPopDep(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamPopDepObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamPopDepObj readPopDep( CFLibDbKeyHash256 pkey ) {
		return( readPopDep( pkey, false ) );
	}

	@Override
	public ICFBamPopDepObj readPopDep( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamPopDepObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamPopDep readRec = schema.getCFBamBackingStore().getTablePopDep().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamPopDepObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamPopDepObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamPopDepObj readCachedPopDep( CFLibDbKeyHash256 pkey ) {
		ICFBamPopDepObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposePopDep( ICFBamPopDepObj obj )
	{
		final String S_ProcName = "CFBamPopDepTableObj.reallyDeepDisposePopDep() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamPopDepObj existing = readCachedPopDep( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamPopDepByRelationIdxKey keyRelationIdx = schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
		keyRelationIdx.setRequiredRelationId( existing.getRequiredRelationId() );

		ICFBamPopDepByDefSchemaIdxKey keyDefSchemaIdx = schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
		keyDefSchemaIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );



		if( indexByRelationIdx != null ) {
			if( indexByRelationIdx.containsKey( keyRelationIdx ) ) {
				indexByRelationIdx.get( keyRelationIdx ).remove( pkey );
				if( indexByRelationIdx.get( keyRelationIdx ).size() <= 0 ) {
					indexByRelationIdx.remove( keyRelationIdx );
				}
			}
		}

		if( indexByDefSchemaIdx != null ) {
			if( indexByDefSchemaIdx.containsKey( keyDefSchemaIdx ) ) {
				indexByDefSchemaIdx.get( keyDefSchemaIdx ).remove( pkey );
				if( indexByDefSchemaIdx.get( keyDefSchemaIdx ).size() <= 0 ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}
		}


		schema.getScopeTableObj().reallyDeepDisposeScope( obj );
	}
	@Override
	public void deepDisposePopDep( CFLibDbKeyHash256 pkey ) {
		ICFBamPopDepObj obj = readCachedPopDep( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamPopDepObj lockPopDep( CFLibDbKeyHash256 pkey ) {
		ICFBamPopDepObj locked = null;
		ICFBamPopDep lockRec = schema.getCFBamBackingStore().getTablePopDep().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamPopDepObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamPopDepObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockPopDep", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamPopDepObj> readAllPopDep() {
		return( readAllPopDep( false ) );
	}

	@Override
	public List<ICFBamPopDepObj> readAllPopDep( boolean forceRead ) {
		final String S_ProcName = "readAllPopDep";
		if( ( allPopDep == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamPopDepObj> map = new HashMap<CFLibDbKeyHash256,ICFBamPopDepObj>();
			allPopDep = map;
			ICFBamPopDep[] recList = schema.getCFBamBackingStore().getTablePopDep().readAllDerived( null );
			ICFBamPopDep rec;
			ICFBamPopDepObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopDepObj realised = (ICFBamPopDepObj)obj.realise();
			}
		}
		int len = allPopDep.size();
		ICFBamPopDepObj arr[] = new ICFBamPopDepObj[len];
		Iterator<ICFBamPopDepObj> valIter = allPopDep.values().iterator();
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
		ArrayList<ICFBamPopDepObj> arrayList = new ArrayList<ICFBamPopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopDepObj> cmp = new Comparator<ICFBamPopDepObj>() {
			@Override
			public int compare( ICFBamPopDepObj lhs, ICFBamPopDepObj rhs ) {
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
		List<ICFBamPopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamPopDepObj> readCachedAllPopDep() {
		final String S_ProcName = "readCachedAllPopDep";
		ArrayList<ICFBamPopDepObj> arrayList = new ArrayList<ICFBamPopDepObj>();
		if( allPopDep != null ) {
			int len = allPopDep.size();
			ICFBamPopDepObj arr[] = new ICFBamPopDepObj[len];
			Iterator<ICFBamPopDepObj> valIter = allPopDep.values().iterator();
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
		Comparator<ICFBamPopDepObj> cmp = new Comparator<ICFBamPopDepObj>() {
			public int compare( ICFBamPopDepObj lhs, ICFBamPopDepObj rhs ) {
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
	public ICFBamPopDepObj readPopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readPopDepByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamPopDepObj readPopDepByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamPopDepObj obj = readPopDep( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamPopDepObj> readPopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readPopDepByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamPopDepObj> readPopDepByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readPopDepByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamPopDepObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopDepObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamPopDepObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopDepObj realised = (ICFBamPopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamPopDepObj arr[] = new ICFBamPopDepObj[len];
		Iterator<ICFBamPopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamPopDepObj> arrayList = new ArrayList<ICFBamPopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopDepObj> cmp = new Comparator<ICFBamPopDepObj>() {
			@Override
			public int compare( ICFBamPopDepObj lhs, ICFBamPopDepObj rhs ) {
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
		List<ICFBamPopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamPopDepObj> readPopDepByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		return( readPopDepByRelationIdx( RelationId,
			false ) );
	}

	@Override
	public List<ICFBamPopDepObj> readPopDepByRelationIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readPopDepByRelationIdx";
		ICFBamPopDepByRelationIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
		key.setRequiredRelationId( RelationId );
		Map<CFLibDbKeyHash256, ICFBamPopDepObj> dict;
		if( indexByRelationIdx == null ) {
			indexByRelationIdx = new HashMap< ICFBamPopDepByRelationIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopDepObj > >();
		}
		if( ( ! forceRead ) && indexByRelationIdx.containsKey( key ) ) {
			dict = indexByRelationIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamPopDepObj>();
			ICFBamPopDepObj obj;
			ICFBamPopDep[] recList = schema.getCFBamBackingStore().getTablePopDep().readDerivedByRelationIdx( null,
				RelationId );
			ICFBamPopDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopDepObj realised = (ICFBamPopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByRelationIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamPopDepObj arr[] = new ICFBamPopDepObj[len];
		Iterator<ICFBamPopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamPopDepObj> arrayList = new ArrayList<ICFBamPopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopDepObj> cmp = new Comparator<ICFBamPopDepObj>() {
			@Override
			public int compare( ICFBamPopDepObj lhs, ICFBamPopDepObj rhs ) {
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
		List<ICFBamPopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamPopDepObj> readPopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readPopDepByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamPopDepObj> readPopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readPopDepByDefSchemaIdx";
		ICFBamPopDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamPopDepObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamPopDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopDepObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamPopDepObj>();
			ICFBamPopDepObj obj;
			ICFBamPopDep[] recList = schema.getCFBamBackingStore().getTablePopDep().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamPopDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopDepObj realised = (ICFBamPopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamPopDepObj arr[] = new ICFBamPopDepObj[len];
		Iterator<ICFBamPopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamPopDepObj> arrayList = new ArrayList<ICFBamPopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopDepObj> cmp = new Comparator<ICFBamPopDepObj>() {
			@Override
			public int compare( ICFBamPopDepObj lhs, ICFBamPopDepObj rhs ) {
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
		List<ICFBamPopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamPopDepObj readCachedPopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamPopDepObj obj = null;
		obj = readCachedPopDep( Id );
		return( obj );
	}

	@Override
	public List<ICFBamPopDepObj> readCachedPopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedPopDepByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamPopDepObj> arrayList = new ArrayList<ICFBamPopDepObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamPopDepObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamPopDepObj arr[] = new ICFBamPopDepObj[len];
				Iterator<ICFBamPopDepObj> valIter = dict.values().iterator();
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
		}
		else {
			ICFBamPopDepObj obj;
			Iterator<ICFBamPopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamPopDepObj> cmp = new Comparator<ICFBamPopDepObj>() {
			@Override
			public int compare( ICFBamPopDepObj lhs, ICFBamPopDepObj rhs ) {
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
	public List<ICFBamPopDepObj> readCachedPopDepByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "readCachedPopDepByRelationIdx";
		ICFBamPopDepByRelationIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
		key.setRequiredRelationId( RelationId );
		ArrayList<ICFBamPopDepObj> arrayList = new ArrayList<ICFBamPopDepObj>();
		if( indexByRelationIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamPopDepObj> dict;
			if( indexByRelationIdx.containsKey( key ) ) {
				dict = indexByRelationIdx.get( key );
				int len = dict.size();
				ICFBamPopDepObj arr[] = new ICFBamPopDepObj[len];
				Iterator<ICFBamPopDepObj> valIter = dict.values().iterator();
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
		}
		else {
			ICFBamPopDepObj obj;
			Iterator<ICFBamPopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamPopDepObj> cmp = new Comparator<ICFBamPopDepObj>() {
			@Override
			public int compare( ICFBamPopDepObj lhs, ICFBamPopDepObj rhs ) {
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
	public List<ICFBamPopDepObj> readCachedPopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedPopDepByDefSchemaIdx";
		ICFBamPopDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamPopDepObj> arrayList = new ArrayList<ICFBamPopDepObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamPopDepObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamPopDepObj arr[] = new ICFBamPopDepObj[len];
				Iterator<ICFBamPopDepObj> valIter = dict.values().iterator();
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
		}
		else {
			ICFBamPopDepObj obj;
			Iterator<ICFBamPopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamPopDepObj> cmp = new Comparator<ICFBamPopDepObj>() {
			@Override
			public int compare( ICFBamPopDepObj lhs, ICFBamPopDepObj rhs ) {
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
	public void deepDisposePopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamPopDepObj obj = readCachedPopDepByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposePopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposePopDepByTenantIdx";
		ICFBamPopDepObj obj;
		List<ICFBamPopDepObj> arrayList = readCachedPopDepByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamPopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposePopDepByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "deepDisposePopDepByRelationIdx";
		ICFBamPopDepObj obj;
		List<ICFBamPopDepObj> arrayList = readCachedPopDepByRelationIdx( RelationId );
		if( arrayList != null )  {
			Iterator<ICFBamPopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposePopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposePopDepByDefSchemaIdx";
		ICFBamPopDepObj obj;
		List<ICFBamPopDepObj> arrayList = readCachedPopDepByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamPopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamPopDepObj updatePopDep( ICFBamPopDepObj Obj ) {
		ICFBamPopDepObj obj = Obj;
		schema.getCFBamBackingStore().getTablePopDep().updatePopDep( null,
			Obj.getPopDepRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getPopDepTableObj().getClassCode() ) {
			obj = (ICFBamPopDepObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deletePopDep( ICFBamPopDepObj Obj ) {
		ICFBamPopDepObj obj = Obj;
		schema.getCFBamBackingStore().getTablePopDep().deletePopDep( null,
			obj.getPopDepRec() );
		Obj.forget();
	}

	@Override
	public void deletePopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamPopDepObj obj = readPopDep(Id);
		if( obj != null ) {
			ICFBamPopDepEditObj editObj = (ICFBamPopDepEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamPopDepEditObj)obj.beginEdit();
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
		deepDisposePopDepByIdIdx( Id );
	}

	@Override
	public void deletePopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopDepObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamPopDepObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTablePopDep().deletePopDepByTenantIdx( null,
				TenantId );
			Iterator<ICFBamPopDepObj> iter = dict.values().iterator();
			ICFBamPopDepObj obj;
			List<ICFBamPopDepObj> toForget = new LinkedList<ICFBamPopDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTablePopDep().deletePopDepByTenantIdx( null,
				TenantId );
		}
		deepDisposePopDepByTenantIdx( TenantId );
	}

	@Override
	public void deletePopDepByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		ICFBamPopDepByRelationIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
		key.setRequiredRelationId( RelationId );
		if( indexByRelationIdx == null ) {
			indexByRelationIdx = new HashMap< ICFBamPopDepByRelationIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopDepObj > >();
		}
		if( indexByRelationIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamPopDepObj> dict = indexByRelationIdx.get( key );
			schema.getCFBamBackingStore().getTablePopDep().deletePopDepByRelationIdx( null,
				RelationId );
			Iterator<ICFBamPopDepObj> iter = dict.values().iterator();
			ICFBamPopDepObj obj;
			List<ICFBamPopDepObj> toForget = new LinkedList<ICFBamPopDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByRelationIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTablePopDep().deletePopDepByRelationIdx( null,
				RelationId );
		}
		deepDisposePopDepByRelationIdx( RelationId );
	}

	@Override
	public void deletePopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamPopDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamPopDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopDepObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamPopDepObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTablePopDep().deletePopDepByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamPopDepObj> iter = dict.values().iterator();
			ICFBamPopDepObj obj;
			List<ICFBamPopDepObj> toForget = new LinkedList<ICFBamPopDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTablePopDep().deletePopDepByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposePopDepByDefSchemaIdx( DefSchemaId );
	}
}